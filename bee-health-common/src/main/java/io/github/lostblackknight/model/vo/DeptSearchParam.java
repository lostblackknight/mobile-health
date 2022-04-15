package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DeptSearchParam {
    private String hospitalCode;
    private String hospitalName;
    private String deptName;
    private String deptCode;
    private String city;
    private String province;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
