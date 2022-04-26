package io.github.lostblackknight.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lostblackknight.model.entity.order.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【order_info(订单表)】的数据库操作Service
* @createDate 2022-04-18 18:49:36
*/
public interface OrderInfoService extends IService<OrderInfo> {

    String createOrder(String scheduleId, Long patientId);

    OrderInfo getOrderInfoByOrderSn(String orderSn);

    void updateOrderStatus(String orderSn, Integer status);

    boolean cancelBooking(String orderSn) throws JsonProcessingException;

    boolean checkBooking(String scheduleId);

    List<OrderInfo> getReceivedOrder();

    List<OrderInfo> getClosedOrder();

    boolean closeOrder(String orderSn);
}
