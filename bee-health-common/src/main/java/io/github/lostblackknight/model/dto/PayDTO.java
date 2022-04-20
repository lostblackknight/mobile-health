package io.github.lostblackknight.model.dto;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class PayDTO {
    // 商户订单号，商户网站订单系统中唯一订单号，必填
    String outTradeNo;
    // 订单名称，必填
    String subject;
    // 付款金额，必填
    String totalAmount;
    // 商品描述，可空
    String body;
    // 超时时间 可空
    String timeoutExpress;
    // 销售产品码 必填
    String productCode;
}
