package io.github.lostblackknight.model.dto;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class RefundDTO {
    //商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
    //商户订单号，和支付宝交易号二选一
    String outTradeNo;
    //支付宝交易号，和商户订单号二选一
    String tradeNo;
    //退款金额，不能大于订单总金额
    String refundAmount;
    //退款的原因说明
    String refundReason;
    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
    String outRequestNo;
}
