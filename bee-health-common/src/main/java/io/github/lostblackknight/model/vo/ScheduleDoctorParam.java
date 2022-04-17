package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ScheduleDoctorParam {
    private String hospitalCode;
    private String deptCode;
    private String date;
    private String doctorName;
    private String city;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
