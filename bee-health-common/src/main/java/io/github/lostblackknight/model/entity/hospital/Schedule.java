package io.github.lostblackknight.model.entity.hospital;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName t_schedule
 */
@TableName(value = "t_schedule")
@Data
public class Schedule implements Serializable {
    /**
     * 排班的id
     */
    @TableId
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

    private String doctorCode;

    private Long memberId;

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
    private String date;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}