package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DoctorDTO {
    private String doctorName;
    private String doctorCode;
    private Long memberId;
    private String expert;
    private List<String> illNameList;
    private String icon;
    private String levelName;
    private Integer status;
    private String deptName;
    private String deptCode;
    private String hospitalName;
    private String hospitalCode;
}
