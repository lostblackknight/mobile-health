package io.github.lostblackknight.thirdparty.template;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.teaopenapi.models.Config;
import io.github.lostblackknight.thirdparty.properties.AliYunProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Component
@RequiredArgsConstructor
public class SmsCodeTemplate {

    private final Client client;

    public String sendCode(String phone, String code) throws Exception {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phone)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        client.sendSms(sendSmsRequest);
        return code;
    }

    @Component
    @RequiredArgsConstructor
    public static class Provider {

        private final AliYunProperties aliYunProperties;

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public Client getSmsCodeClient() throws Exception {
            final AliYunProperties.Sms sms = aliYunProperties.getSms();
            Config config = new Config()
                    .setAccessKeyId(sms.getAccessKeyId())
                    .setAccessKeySecret(sms.getAccessKeySecret());
            // 访问的域名
            config.endpoint = sms.getEndpoint();
            return new Client(config);
        }
    }
}
