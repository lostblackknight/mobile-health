package io.github.lostblackknight.mock.controller;

import cn.hutool.core.date.DateUtil;
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
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@Slf4j
public class ApiController {

    @GetMapping("/test")
    public Hospital test() throws IOException {
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream("data/北京天使儿童医院-hospital.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        final Hospital hospital = mapper.readValue(is, Hospital.class);
        return hospital;
    }

    @GetMapping(path = "/hospitals/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadHospitalData(HospitalClientDetailVO vo) throws IOException {
        final BeeHealthTemplate template = new BeeHealthTemplate(vo.getClientId(), vo.getSecret());
        final String token = template.getToken();

        final InputStream is = this.getClass().getClassLoader().getResourceAsStream("data/" + vo.getHospitalName() + "-hospital.json");
//        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-hospital.json");
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

        final InputStream is = this.getClass().getClassLoader().getResourceAsStream("data/" + vo.getHospitalName() + "-dept.json");
//        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-dept.json");
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

    private String getWeek(int i) {
        String week = "";
        switch (i) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
        }
        return week;
    }

    @GetMapping(path = "/schedules/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadScheduleData(HospitalClientDetailVO vo) throws IOException {
        final BeeHealthTemplate template = new BeeHealthTemplate(vo.getClientId(), vo.getSecret());
        final String token = template.getToken();

        final InputStream is = this.getClass().getClassLoader().getResourceAsStream("data/" + vo.getHospitalName() + "-schedule.json");
//        final FileInputStream is = new FileInputStream("data/" + vo.getHospitalName() + "-schedule.json");
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
            if (schedule.getDoctorName().equals("张丽")) {
                schedule.setMemberId(3L);
            }
            if (schedule.getDoctorName().equals("王波")) {
                schedule.setMemberId(2L);
            }
            if (schedule.getMemberId() == null) {
                schedule.setMemberId(0L);
            }
            // 跟新日期
            final String date = schedule.getDate();

            final Date currentDate = new Date();

            switch (date) {
                case "2022-04-16":
                    final String format = DateUtil.format(currentDate, "yyyy-MM-dd");
                    schedule.setDate(format);
                    final int week = DateUtil.dayOfWeek(currentDate);
                    schedule.setWeek(getWeek(week));
                    break;
                case "2022-04-17":
                    final String format1 = DateUtil.format(DateUtil.offsetDay(currentDate, 1), "yyyy-MM-dd");
                    schedule.setDate(format1);
                    final int week1 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 1));
                    schedule.setWeek(getWeek(week1));
                    break;
                case "2022-04-18":
                    final String format2 = DateUtil.format(DateUtil.offsetDay(currentDate, 2), "yyyy-MM-dd");
                    schedule.setDate(format2);
                    final int week2 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 2));
                    schedule.setWeek(getWeek(week2));
                    break;
                case "2022-04-19":
                    final String format3 = DateUtil.format(DateUtil.offsetDay(currentDate, 3), "yyyy-MM-dd");
                    schedule.setDate(format3);
                    final int week3 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 3));
                    schedule.setWeek(getWeek(week3));
                    break;
                case "2022-04-20":
                    final String format4 = DateUtil.format(DateUtil.offsetDay(currentDate, 4), "yyyy-MM-dd");
                    schedule.setDate(format4);
                    final int week4 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 4));
                    schedule.setWeek(getWeek(week4));
                    break;
                case "2022-04-21":
                    final String format5 = DateUtil.format(DateUtil.offsetDay(currentDate, 5), "yyyy-MM-dd");
                    schedule.setDate(format5);
                    final int week5 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 5));
                    schedule.setWeek(getWeek(week5));
                    break;
                case "2022-04-22":
                    final String format6 = DateUtil.format(DateUtil.offsetDay(currentDate, 6), "yyyy-MM-dd");
                    schedule.setDate(format6);
                    final int week6 = DateUtil.dayOfWeek(DateUtil.offsetDay(currentDate, 6));
                    schedule.setWeek(getWeek(week6));
                    break;
            }

            result = HttpUtil.createPost(BeeHealthTemplate.HOSPITAL_API + "/schedules/upload")
                    .auth(BeeHealthTemplate.AUTH_PREFIX + token)
                    .body(new ObjectMapper().writeValueAsString(schedule))
                    .execute()
                    .body();
        }
        return result;
    }
}
