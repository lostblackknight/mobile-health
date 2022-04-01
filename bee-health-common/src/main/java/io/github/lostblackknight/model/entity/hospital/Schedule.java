package io.github.lostblackknight.model.entity.hospital;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 排班
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ApiModel(description = "排班")
public class Schedule {

    @ApiModelProperty(value = "医院编号")
    private String hospitalCode;

    @ApiModelProperty(value = "科室编号")
    private String departmentCode;

    @ApiModelProperty(value = "排班编号(医院自己的排班主键)")
    private String hosScheduleId;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "医生名称")
    private String doctorName;

    @ApiModelProperty(value = "擅长技能")
    private String skill;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "排班日期")
    private Date workDate;

    @ApiModelProperty(value = "排班时间(0: 上午 1: 下午)")
    private Integer workTime;

    @ApiModelProperty(value = "可预约数")
    private Integer reservedNumber;

    @ApiModelProperty(value = "剩余预约数")
    private Integer availableNumber;

    @ApiModelProperty(value = "挂号费")
    private BigDecimal amount;

    @ApiModelProperty(value = "排班状态(-1：停诊 0：停约 1：可约)")
    private Integer status;

    private static final long serialVersionUID = 1L;
}
