package io.github.lostblackknight;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lostblackknight.mock.support.BeeHealthTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest
class BeeHealthMockApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        String clientId = "1a903469ecdb481ea05651be04312ad0";
        String clientSecret = "d6b382c1-2524-49d5-b1dc-2c01790bbbd8";
        final BeeHealthTemplate template = new BeeHealthTemplate(clientId, clientSecret);

        final String token = template.getToken();
        System.out.println(token);
    }

    @Test
    void test() {
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd"));
    }

}
