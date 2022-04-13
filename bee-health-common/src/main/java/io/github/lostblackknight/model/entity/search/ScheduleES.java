package io.github.lostblackknight.model.entity.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ScheduleES {
    /**
     * 排班的id
     */
    private String scheduleId;

    /**
     * 医院的编码
     */
    private String hospitalCode;

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 科室的编码
     */
    private String deptCode;

    /**
     * 科室的名称
     */
    private String deptName;

    /**
     * 医生的名称
     */
    private String doctorName;

    /**
     * 医生的专长
     */
    private String expert;

    /**
     * 专治的病情
     */
    private String illNameList;

    /**
     * 医生的头像
     */
    private String icon;

    /**
     * 医生的职称
     */
    private String levelName;

    /**
     * 排班日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 排班星期
     */
    private String week;

    /**
     * 排班的时间(am, pm, null)
     */
    private String timeType;

    /**
     * 挂号费
     */
    private BigDecimal amount;

    /**
     * 预约状态
     */
    private Integer yuYueState;

    /**
     * 最大预约数
     */
    private Integer yuYueMax;

    /**
     * 已预约的数量
     */
    private Integer yuYueNum;
}
