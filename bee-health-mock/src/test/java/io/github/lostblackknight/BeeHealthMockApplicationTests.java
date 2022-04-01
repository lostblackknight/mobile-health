package io.github.lostblackknight;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lostblackknight.mock.support.BeeHealthTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class BeeHealthMockApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        String clientId = "1a903469ecdb481ea05651be04312ad0";
        String clientSecret = "d6b382c1-2524-49d5-b1dc-2c01790bbbd8";
        final BeeHealthTemplate template = new BeeHealthTemplate(clientId, clientSecret);

        final String token = template.getToken();
        System.out.println(token);
        final String refreshToken = template.getRefreshToken();
        System.out.println(refreshToken);
    }

}
