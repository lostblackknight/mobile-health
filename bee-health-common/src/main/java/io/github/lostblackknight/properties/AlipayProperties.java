package io.github.lostblackknight.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayProperties {
    /**
     * 商户appid
     */
    private String appId;
    /**
     * app私钥
     */
    private String appPrivateKey;
    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    private String notifyUrl;
    /**
     * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    private String returnUrl;
    /**
     * 请求网关地址
     */
    private String gatewayUrl;
    /**
     * 编码
     */
    private final String charset = "UTF-8";
    /**
     * 返回格式
     */
    private final String format = "json";
    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;
    /**
     * RSA2
     */
    private String signType = "RSA2";
}
