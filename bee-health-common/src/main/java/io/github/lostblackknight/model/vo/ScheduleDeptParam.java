package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ScheduleDeptParam {
    private String hospitalCode;
    private String doctorCode;
    private Integer pageNum = 1;
    private Integer pageSize = 100;
}
