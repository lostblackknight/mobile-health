package io.github.lostblackknight.search.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DoctorScheduleDTO {
    private String hospitalCode;
    private String hospitalName;
    private String deptCode;
    private String deptName;
    private String doctorCode;
    private String doctorName;
    private Long memberId;
    private String expert;
    private List<String> illNameList;
    private String icon;
    private String levelName;
    private List<Schedule> schedules;

    @Data
    public static class Schedule {
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private Date date;
        private String day;
        private String week;
        private ScheduleDetail am;
        private ScheduleDetail pm;
    }

    @Data
    public static class ScheduleDetail {
        private String scheduleId;
        private String timeType;
        private BigDecimal amount;
        private Integer yuYueState;
        private Integer yuYueMax;
        private Integer yuYueNum;
    }
}
