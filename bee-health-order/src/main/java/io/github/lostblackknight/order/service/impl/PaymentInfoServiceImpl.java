package io.github.lostblackknight.order.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.constant.OrderConstant;
import io.github.lostblackknight.model.dto.PayAsyncDTO;
import io.github.lostblackknight.model.entity.order.PaymentInfo;
import io.github.lostblackknight.order.service.OrderInfoService;
import io.github.lostblackknight.order.service.PaymentInfoService;
import io.github.lostblackknight.order.mapper.PaymentInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
* @author chensixiang
* @description 针对表【payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2022-04-19 15:41:39
*/
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService{

    private final OrderInfoService orderInfoService;

    @Override
    public void handlePayResult(PayAsyncDTO payAsyncDTO) throws JsonProcessingException {
        // 支付成功添加相关记录
        final PaymentInfo info = new PaymentInfo();
        info.setOrderSn(payAsyncDTO.getOut_trade_no());
        info.setOrderId(null);
        info.setPaymentType(1);
        info.setTradeNo(payAsyncDTO.getTrade_no());
        info.setTotalAmount(new BigDecimal(payAsyncDTO.getTotal_amount()));
        info.setSubject(payAsyncDTO.getSubject());
        info.setPaymentStatus(payAsyncDTO.getTrade_status());
        info.setCallbackTime(DateUtil.parse(payAsyncDTO.getNotify_time(),"yyyy-MM-dd HH:mm:ss"));
        info.setCallbackContent(new ObjectMapper().writeValueAsString(payAsyncDTO));
        baseMapper.insert(info);
        // 修改订单状态
        if ("TRADE_SUCCESS".equals(payAsyncDTO.getTrade_status()) || "TRADE_FINISHED".equals(payAsyncDTO.getTrade_status())) {
            String orderSn = payAsyncDTO.getOut_trade_no();
            orderInfoService.updateOrderStatus(orderSn, OrderConstant.PAID);
        }
    }

    @Override
    public PaymentInfo getByOrderSn(String orderSn) {
        return baseMapper.selectOne(new QueryWrapper<PaymentInfo>().eq("order_sn", orderSn));
    }
}




