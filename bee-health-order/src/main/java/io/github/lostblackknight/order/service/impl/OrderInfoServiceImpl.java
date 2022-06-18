package io.github.lostblackknight.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.constant.OrderConstant;
import io.github.lostblackknight.model.dto.PayRefundResponseDTO;
import io.github.lostblackknight.model.dto.RefundDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.hospital.Schedule;
import io.github.lostblackknight.model.entity.member.Patient;
import io.github.lostblackknight.model.entity.order.OrderInfo;
import io.github.lostblackknight.model.entity.order.PaymentInfo;
import io.github.lostblackknight.model.entity.order.RefundInfo;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.order.client.HospitalClient;
import io.github.lostblackknight.order.client.MemberClient;
import io.github.lostblackknight.order.service.OrderInfoService;
import io.github.lostblackknight.order.mapper.OrderInfoMapper;
import io.github.lostblackknight.order.service.PaymentInfoService;
import io.github.lostblackknight.order.service.RefundInfoService;
import io.github.lostblackknight.order.support.AlipayTemplate;
import io.github.lostblackknight.order.support.TokenInfoContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author chensixiang
 * @description 针对表【order_info(订单表)】的数据库操作Service实现
 * @createDate 2022-04-18 18:49:36
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
        implements OrderInfoService {

    private final HospitalClient hospitalClient;

    private final MemberClient memberClient;

    private final AlipayTemplate alipayTemplate;

    private final RefundInfoService refundInfoService;

    @Resource
    private PaymentInfoService paymentInfoService;

    @Override
    public String createOrder(String scheduleId, Long patientId) {
        final OrderInfo orderInfo = new OrderInfo();

        // 获取信息
        final CommonResult<Schedule> commonResult = hospitalClient.getScheduleById(scheduleId);
        if (commonResult.getCode() == 1) {
            final Schedule schedule = commonResult.getData();
            if (schedule.getYuYueState() == 0) {
                // 不可预约
                return null;
            }
            final Date currentDate = new Date();
            final Date date = DateUtil.parse(schedule.getDate(), "yyyy-MM-dd").toJdkDate();
            final int month = DateUtil.month(date);
            final int currentDay = DateUtil.dayOfMonth(currentDate);
            final int currentMonth = DateUtil.month(currentDate);
            final int day = DateUtil.dayOfMonth(date);

            if (day < currentDay && month == currentMonth) {
                return null;
            }
            final int hour = DateUtil.hour(currentDate, true);
            if (schedule.getTimeType().equals("am") && day == currentDay) {
                return null;
            } else if (schedule.getTimeType().equals("pm") && hour >= 12 & day == currentDay) {
                return null;
            }

            final TokenInfoDTO dto = TokenInfoContextHolder.get();
            orderInfo.setMemberId(dto.getUid());
            orderInfo.setOrderSn(IdUtil.getSnowflake(1L, 5L).nextIdStr());
            orderInfo.setHospitalCode(schedule.getHospitalCode());
            orderInfo.setHospitalName(schedule.getHospitalName());
            orderInfo.setDeptCode(schedule.getDeptCode());
            orderInfo.setDeptName(schedule.getDeptName());
            orderInfo.setDoctorMemberId(schedule.getMemberId());
            orderInfo.setDoctorCode(schedule.getDoctorCode());
            orderInfo.setDoctorName(schedule.getDoctorName());
            orderInfo.setLevelName(schedule.getLevelName());
            orderInfo.setScheduleId(schedule.getScheduleId());
            orderInfo.setReserveDate(DateUtil.parse(schedule.getDate(), "yyyy-MM-dd"));
            orderInfo.setReserveTime(Objects.equals(schedule.getTimeType(), "am") ? 0 : 1);
            orderInfo.setAmount(schedule.getAmount());

            final CommonResult<Patient> commonResult1 = memberClient.getPatient(patientId);

            if (commonResult1.getCode() == 1) {
                final Patient patient = commonResult1.getData();
                orderInfo.setPatientId(patient.getId());
                orderInfo.setPatientName(patient.getName());
                orderInfo.setPatientPhone(patient.getPhone());
            }

            // TODO 获取医院签名，去医院下单，获取医院信息，这里直接设置
            orderInfo.setRecordId(IdUtil.simpleUUID());
            orderInfo.setNumber(schedule.getYuYueNum() + 1);

            // 设置订单状态
            orderInfo.setOrderStatus(OrderConstant.UNPAID);

            // orderInfo.setQuitTime();
            // 下单成功，跟新排班信息，发送短信 TODO 分布式事务
            final Schedule newSchedule = new Schedule();
            BeanUtil.copyProperties(schedule, newSchedule);
            newSchedule.setYuYueNum(schedule.getYuYueNum() + 1);
            Integer state = schedule.getYuYueNum() - schedule.getYuYueMax() > 0 ? 0 : 1;
            newSchedule.setYuYueState(state);
            hospitalClient.modifySchedule(newSchedule);
            baseMapper.insert(orderInfo);
            return orderInfo.getOrderSn();
        }
        return null;
    }

    @Override
    public OrderInfo getOrderInfoByOrderSn(String orderSn) {
        return baseMapper.selectOne(new QueryWrapper<OrderInfo>().eq("order_sn", orderSn));
    }

    @Override
    public void updateOrderStatus(String orderSn, Integer status) {
        final OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus(status);
        baseMapper.update(orderInfo, new QueryWrapper<OrderInfo>().eq("order_sn", orderSn));
    }

    @Override
    public boolean cancelBooking(String orderSn) throws JsonProcessingException {
        // 查看当前状态
        final OrderInfo orderInfo = baseMapper.selectOne(new QueryWrapper<OrderInfo>().eq("order_sn", orderSn));

        if (Objects.equals(orderInfo.getOrderStatus(), OrderConstant.UNPAID)) {
            // 预约成功，未支付，修改预约数量 TODO 分布式事务
            final CommonResult<Schedule> commonResult = hospitalClient.getScheduleById(orderInfo.getScheduleId());
            if (commonResult.getCode() == 1) {
                final Schedule data = commonResult.getData();
                data.setYuYueNum(data.getYuYueNum() - 1);
                data.setYuYueState(1);
                final CommonResult<?> commonResult1 = hospitalClient.modifySchedule(data);
                if (commonResult1.getCode() == 1) {
                    // 修改订单状态
                    final OrderInfo oi = new OrderInfo();
                    oi.setOrderStatus(OrderConstant.CANCEL);
                    baseMapper.update(oi, new QueryWrapper<OrderInfo>().eq("order_sn", orderSn));
                    return true;
                }
            }

        } else if (Objects.equals(orderInfo.getOrderStatus(), OrderConstant.PAID)) {
            // 已支付，修改预约数量，退款 TODO 分布式事务
            final CommonResult<Schedule> commonResult = hospitalClient.getScheduleById(orderInfo.getScheduleId());
            if (commonResult.getCode() == 1) {
                final Schedule data = commonResult.getData();
                data.setYuYueNum(data.getYuYueNum() - 1);
                data.setYuYueState(1);
                final CommonResult<?> commonResult1 = hospitalClient.modifySchedule(data);
                if (commonResult1.getCode() == 1) {
                    // 退款 TODO 通知退款成功 ?
                    final RefundDTO dto = new RefundDTO();
                    dto.setOutTradeNo(orderSn);
                    dto.setRefundAmount(orderInfo.getAmount().toString());
                    final PayRefundResponseDTO refund = alipayTemplate.refund(dto);
                    // 添加退款几录
                    PaymentInfo paymentInfo = paymentInfoService.getByOrderSn(orderInfo.getOrderSn());
                    final RefundInfo refundInfo = new RefundInfo();
                    refundInfo.setOrderSn(paymentInfo.getOrderSn());
                    refundInfo.setPaymentType(paymentInfo.getPaymentType());
                    refundInfo.setTradeNo(paymentInfo.getTradeNo());
                    refundInfo.setTotalAmount(paymentInfo.getTotalAmount());
                    refundInfo.setSubject(paymentInfo.getSubject());
                    refundInfo.setRefundStatus(1);
                    refundInfo.setCallbackContent(new ObjectMapper().writeValueAsString(paymentInfo));
                    refundInfo.setCallbackTime(new Date());
                    refundInfoService.save(refundInfo);

                    // 修改订单状态
                    final OrderInfo oi = new OrderInfo();
                    oi.setOrderStatus(OrderConstant.CANCEL_UNPAID);
                    baseMapper.update(oi, new QueryWrapper<OrderInfo>().eq("order_sn", orderSn));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkBooking(String scheduleId) {
        final CommonResult<Schedule> result = hospitalClient.getScheduleById(scheduleId);
        if (result.getCode() == 1) {
            final Schedule schedule = result.getData();
            if (schedule.getYuYueState() == 0) {
                // 不可预约
                return false;
            }
            final Date currentDate = new Date();
            final Date date = DateUtil.parse(schedule.getDate(), "yyyy-MM-dd").toJdkDate();
            final int month = DateUtil.month(date);
            final int currentDay = DateUtil.dayOfMonth(currentDate);
            final int currentMonth = DateUtil.month(currentDate);
            final int day = DateUtil.dayOfMonth(date);

            if (day < currentDay && month == currentMonth) {
                return false;
            }
            final int hour = DateUtil.hour(currentDate, true);
            if (schedule.getTimeType().equals("am") && day == currentDay) {
                return false;
            } else if (schedule.getTimeType().equals("pm") && hour >= 12 & day == currentDay) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderInfo> getReceivedOrder() {
        final Long memberId = TokenInfoContextHolder.get().getUid();
        return baseMapper.selectList(new QueryWrapper<OrderInfo>().eq("doctor_member_id", memberId)
                .eq("order_status", OrderConstant.PAID));
    }

    @Override
    public List<OrderInfo> getClosedOrder() {
        final Long memberId = TokenInfoContextHolder.get().getUid();
        return baseMapper.selectList(new QueryWrapper<OrderInfo>().eq("doctor_member_id", memberId)
                .eq("order_status", OrderConstant.CLOSE));
    }

    @Override
    public boolean closeOrder(String orderSn) {
        final OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderSn(orderSn);
        orderInfo.setOrderStatus(OrderConstant.CLOSE);
        return SqlHelper.retBool(baseMapper.update(orderInfo, new QueryWrapper<OrderInfo>().eq("order_sn", orderSn)));
    }

    @Override
    public List<OrderInfo> getOrderByStatusList(List<Integer> status) {
        final ArrayList<OrderInfo> list = new ArrayList<>();
        for (Integer s : status) {
            final List<OrderInfo> orderInfos = baseMapper.selectList(new QueryWrapper<OrderInfo>().eq("order_status", s));
            list.addAll(orderInfos);
        }
        return list;
    }

    @Override
    public long getOrderByDate(Date date) {
        final String from = DateUtil.format(date, "yyyy-MM-dd") + " 00:00:00";
        final String end = DateUtil.format(date, "yyyy-MM-dd") + " 23:59:59";
        return baseMapper.selectCount(new QueryWrapper<OrderInfo>().between("create_time", from, end));
    }
}




