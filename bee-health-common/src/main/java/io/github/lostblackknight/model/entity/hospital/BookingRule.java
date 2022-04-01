package io.github.lostblackknight.model.entity.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 预约规则
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ApiModel(description = "预约规则")
public class BookingRule implements Serializable {

    @ApiModelProperty(value = "预约周期")
    private Integer cycle;

    @ApiModelProperty(value = "放号时间")
    private String releaseTime;

    @ApiModelProperty(value = "停挂时间")
    private String stopTime;

    @ApiModelProperty(value = "退号截止天数(如: 就诊前一天为-1，当天为0)")
    private Integer quitDay;

    @ApiModelProperty(value = "退号时间")
    private String quitTime;

    @ApiModelProperty(value = "其他预约规则")
    private List<String> rules;

    private static final long serialVersionUID = 1L;
}
