package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class HospitalSearchParam {
    private String hospitalName;
    private String hospitalCode;
    private String province;
    private String city;
    private String levelName;
    private String className;
}
