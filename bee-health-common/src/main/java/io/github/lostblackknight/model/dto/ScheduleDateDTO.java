package io.github.lostblackknight.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ScheduleDateDTO {
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;
    private String week;
    // 是否可预约
    private Integer status;
}
