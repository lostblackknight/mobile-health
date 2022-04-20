package io.github.lostblackknight.order.support;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.dto.PayDTO;
import io.github.lostblackknight.model.dto.PayRefundResponseDTO;
import io.github.lostblackknight.model.dto.RefundDTO;
import io.github.lostblackknight.properties.AlipayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Component
@RequiredArgsConstructor
public class AlipayTemplate implements InitializingBean {

    private final AlipayProperties alipayProperties;

    private AlipayClient client;

    @Override
    public void afterPropertiesSet() {
        this.client = new DefaultAlipayClient(alipayProperties.getGatewayUrl(),
                alipayProperties.getAppId(),
                alipayProperties.getAppPrivateKey(),
                alipayProperties.getFormat(),
                alipayProperties.getCharset(),
                alipayProperties.getAlipayPublicKey(),
                alipayProperties.getSignType());
    }

    /**
     * 支付
     *
     * @return 支付页面
     */
    public String pay(PayDTO payDTO) {
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(payDTO.getOutTradeNo());
        model.setSubject(payDTO.getSubject());
        model.setTotalAmount(payDTO.getTotalAmount());
        model.setBody(payDTO.getBody());
        model.setTimeoutExpress(payDTO.getTimeoutExpress());
        model.setProductCode(payDTO.getProductCode());

        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setBizModel(model);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        // 设置同步地址
        alipayRequest.setReturnUrl(alipayProperties.getReturnUrl());

        // form表单生产
        String form = "支付失败";
        try {
            // 调用SDK生成表单
            form = this.client.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return form;
    }


    /**
     * 退款
     * @param refundDTO
     */
    public PayRefundResponseDTO refund(RefundDTO refundDTO) {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        model.setOutTradeNo(refundDTO.getOutTradeNo());
        model.setTradeNo(refundDTO.getTradeNo());
        model.setRefundAmount(refundDTO.getRefundAmount());
        model.setRefundReason(refundDTO.getRefundReason());
        model.setOutRequestNo(refundDTO.getOutRequestNo());
        alipayRequest.setBizModel(model);

        AlipayTradeRefundResponse alipay_response = null;
        try {
            alipay_response = client.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        try {
            return new ObjectMapper().readValue(alipay_response.getBody(), PayRefundResponseDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
