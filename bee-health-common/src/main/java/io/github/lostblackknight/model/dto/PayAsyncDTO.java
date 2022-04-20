package io.github.lostblackknight.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class PayAsyncDTO {
    private String  gmt_create;
    private String charset;
    private String seller_email;
    private String subject;
    private String sign;
    private String body;//订单的信息
    private String buyer_id;//支付者的id
    private String invoice_amount;//支付金额
    private String notify_id;//通知id
    private String fund_bill_list;
    private String notify_type;//通知类型； trade_status_sync
    private String trade_status;//交易状态  TRADE_SUCCESS
    private String receipt_amount;//商家收到的款
    private String buyer_pay_amount;//最终支付的金额
    private String app_id;//应用id
    private String seller_id;//商家的id
    private String  gmt_payment;
    private String  notify_time;
    private String version;
    private String out_trade_no;//订单号
    private String total_amount;//支付的总额
    private String trade_no;//流水号
    private String sign_type;//签名类型
    private String auth_app_id;
    private String buyer_logon_id;
    private String point_amount;
}