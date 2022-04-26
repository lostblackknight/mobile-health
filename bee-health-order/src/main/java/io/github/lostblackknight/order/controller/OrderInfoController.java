package io.github.lostblackknight.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lostblackknight.constant.OrderConstant;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.entity.order.OrderInfo;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.order.service.OrderInfoService;
import io.github.lostblackknight.order.support.TokenInfoContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class OrderInfoController {

    private final OrderInfoService orderInfoService;

    @GetMapping("/orders/{orderSn}")
    public CommonResult<?> getOrderDetailByOrderSn(@PathVariable String orderSn) {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final OrderInfo orderInfo = orderInfoService.getOne(new QueryWrapper<OrderInfo>().eq("member_id", dto.getUid())
                .eq("order_sn", orderSn));
        return CommonResult.success(orderInfo);
    }

    @GetMapping("/orders/{memberId}/{orderSn}")
    public CommonResult<?> getOrderDetailByOrderSnAndMemberId(@PathVariable String orderSn, @PathVariable Long memberId) {
        final OrderInfo orderInfo = orderInfoService.getOne(new QueryWrapper<OrderInfo>().eq("member_id", memberId)
                .eq("order_sn", orderSn));
        return CommonResult.success(orderInfo);
    }

    @GetMapping("/orders/check/booking/{scheduleId}")
    public CommonResult<?> checkBooking(@PathVariable String scheduleId) {

        final boolean flag = orderInfoService.checkBooking(scheduleId);

        return flag ? CommonResult.success("") :CommonResult.fail("当前时间不可预约");
    }


    @PostMapping("/orders/{scheduleId}/{patientId}")
    public CommonResult<?> createOrder(@PathVariable String scheduleId, @PathVariable Long patientId) {
        String orderSn = orderInfoService.createOrder(scheduleId, patientId);
        return orderSn != null ? CommonResult.success(Map.of("orderSn", orderSn), "预约成功") : CommonResult.fail("当前时间不可预约");
    }

    @GetMapping("/orders/memberId")
    public CommonResult<List<OrderInfo>> getOrder(@RequestParam(required = false) Integer status) {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final List<OrderInfo> orderInfoList = orderInfoService.list(new QueryWrapper<OrderInfo>().eq("member_id", dto.getUid())
                .eq(status != null, "order_status", status));
        final List<OrderInfo> collect = orderInfoList.stream().filter(orderInfo ->
                Objects.equals(orderInfo.getOrderStatus(), OrderConstant.PAID)
                        || Objects.equals(orderInfo.getOrderStatus(), OrderConstant.UNPAID)
                        || Objects.equals(orderInfo.getOrderStatus(), OrderConstant.CANCEL_UNPAID)
        ).collect(Collectors.toList());
        return CommonResult.success(collect);
    }

    @GetMapping("/orders/memberId/record")
    public CommonResult<List<OrderInfo>> getRecord(@RequestParam Integer status) {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final List<OrderInfo> orderInfoList = orderInfoService.list(new QueryWrapper<OrderInfo>().eq("member_id", dto.getUid()));
        List<OrderInfo> collect = new ArrayList<>();
        if (Objects.equals(status, 0)) {
            collect = orderInfoList.stream().filter(orderInfo ->
                    Objects.equals(orderInfo.getOrderStatus(), OrderConstant.PAID)
                            || Objects.equals(orderInfo.getOrderStatus(), OrderConstant.UNPAID)
            ).collect(Collectors.toList());
        } else if (Objects.equals(status, 1)) {
            collect = orderInfoList.stream().filter(orderInfo ->
                    Objects.equals(orderInfo.getOrderStatus(), OrderConstant.CLOSE)
            ).collect(Collectors.toList());
        } else if (Objects.equals(status, 2)) {
            collect = orderInfoList;
        }
        return CommonResult.success(collect);
    }

    @PutMapping("/orders/cancel/{orderSn}")
    public CommonResult<?> cancelBooking(@PathVariable String orderSn) throws JsonProcessingException {
        final boolean flag = orderInfoService.cancelBooking(orderSn);
        return flag ? CommonResult.success("取消成功") : CommonResult.fail("取消失败");
    }

    @GetMapping("/orders/received")
    public CommonResult<?> getReceivedOrder() {
        List<OrderInfo> orderInfos = orderInfoService.getReceivedOrder();
        return CommonResult.success(orderInfos);
    }

    @PutMapping("/orders/close/{orderSn}")
    public CommonResult<?> closeOrder(@PathVariable String orderSn) {
        boolean flag = orderInfoService.closeOrder(orderSn);
        return flag ? CommonResult.success("关闭成功"): CommonResult.fail("关闭失败");
    }

    @GetMapping("/orders/closed")
    public CommonResult<?> getClosedOrder() {
        List<OrderInfo> orderInfos = orderInfoService.getClosedOrder();
        return CommonResult.success(orderInfos);
    }
}
