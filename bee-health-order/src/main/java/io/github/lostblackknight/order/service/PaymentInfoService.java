package io.github.lostblackknight.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lostblackknight.model.dto.PayAsyncDTO;
import io.github.lostblackknight.model.entity.order.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chensixiang
* @description 针对表【payment_info(支付信息表)】的数据库操作Service
* @createDate 2022-04-19 15:41:39
*/
public interface PaymentInfoService extends IService<PaymentInfo> {

    void handlePayResult(PayAsyncDTO payAsyncDTO) throws JsonProcessingException;

    PaymentInfo getByOrderSn(String orderSn);
}
