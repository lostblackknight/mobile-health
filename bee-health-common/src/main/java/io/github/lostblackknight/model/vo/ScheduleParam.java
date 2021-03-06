package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ScheduleParam {
    private String scheduleId;
    private String hospitalCode;
    private String deptCode;
    private String doctorCode;
    private Long memberId;
    private String date;
    private Integer pageNum = 1;
    private Integer pageSize = 100;
}
