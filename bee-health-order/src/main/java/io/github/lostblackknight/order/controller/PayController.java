package io.github.lostblackknight.order.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.dto.PayAsyncDTO;
import io.github.lostblackknight.model.dto.PayDTO;
import io.github.lostblackknight.model.entity.order.OrderInfo;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.order.service.OrderInfoService;
import io.github.lostblackknight.order.service.PaymentInfoService;
import io.github.lostblackknight.order.support.AlipayTemplate;
import io.github.lostblackknight.order.support.AlipayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PaymentInfoService paymentInfoService;

    private final AlipayTemplate alipayTemplate;

    private final OrderInfoService orderInfoService;

    private final AlipayProperties alipayProperties;

    @PostMapping("/payment/{orderSn}")
    public CommonResult<?> pay(@PathVariable String orderSn) {
        // 调用支付
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderSn(orderSn);
        final PayDTO payDTO = new PayDTO();
        payDTO.setOutTradeNo(orderInfo.getOrderSn());
        payDTO.setSubject("在线挂号");
        payDTO.setTotalAmount(orderInfo.getAmount().toString());
        payDTO.setBody(orderInfo.getHospitalName());
        payDTO.setProductCode(orderInfo.getHospitalCode());
        final String result = alipayTemplate.pay(payDTO);
        return CommonResult.success(Map.of("form", result));
    }

    @PostMapping("/payment/notify")
    public String payNotify(PayAsyncDTO payAsyncDTO, HttpServletRequest request) throws AlipayApiException, JsonProcessingException {
        // 给支付宝返回success后，不再通知
        log.info("通知的数据：{}", new ObjectMapper().writeValueAsString(payAsyncDTO));
        HashMap<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean checkV1 = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), alipayProperties.getCharset(), alipayProperties.getSignType());
        if (checkV1) {
            log.info("签名验证成功");
            paymentInfoService.handlePayResult(payAsyncDTO);
            return "success";
        } else {
            log.info("签名验证失败");
            return "fail";
        }
    }
}
