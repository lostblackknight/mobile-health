package io.github.lostblackknight.mock.controller;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.github.lostblackknight.mock.model.*;
import io.github.lostblackknight.mock.support.BeeHealthTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@Slf4j
public class ApiController {

    @GetMapping(path = "/hospitals/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadHospitalData(HospitalClientDetailVO vo) throws IOException {
        final BeeHealthTemplate template = new BeeHealthTemplate(vo.getClientId(), vo.getSecret());
        final String token = template.getToken();

        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-hospital.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        final Hospital hospital = mapper.readValue(is, Hospital.class);

        return HttpUtil.createPost(BeeHealthTemplate.HOSPITAL_API + "/hospitals/upload")
                .auth(BeeHealthTemplate.AUTH_PREFIX + token)
                .body(new ObjectMapper().writeValueAsString(hospital))
                .execute()
                .body();
    }

    @GetMapping(path = "/depts/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadDeptData(HospitalClientDetailVO vo) throws IOException {
        final BeeHealthTemplate template = new BeeHealthTemplate(vo.getClientId(), vo.getSecret());
        final String token = template.getToken();

        log.info("申请的token为：{}", token);

        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-dept.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        final List<Dept> depts = mapper.readValue(is, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        String result = "";

        for (Dept dept : depts) {
            result = HttpUtil.createPost(BeeHealthTemplate.HOSPITAL_API + "/depts/upload")
                    .auth(BeeHealthTemplate.AUTH_PREFIX + token)
                    .body(new ObjectMapper().writeValueAsString(dept))
                    .execute()
                    .body();
        }
        return result;
    }

    @GetMapping(path = "/schedules/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadScheduleData(HospitalClientDetailVO vo) throws IOException {
        final BeeHealthTemplate template = new BeeHealthTemplate(vo.getClientId(), vo.getSecret());
        final String token = template.getToken();

        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-schedule.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        final List<Schedule> schedules = mapper.readValue(is, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        String result = "";

        for (Schedule schedule : schedules) {
            result = HttpUtil.createPost(BeeHealthTemplate.HOSPITAL_API + "/schedules/upload")
                    .auth(BeeHealthTemplate.AUTH_PREFIX + token)
                    .body(new ObjectMapper().writeValueAsString(schedule))
                    .execute()
                    .body();
        }
        return result;
    }
}
