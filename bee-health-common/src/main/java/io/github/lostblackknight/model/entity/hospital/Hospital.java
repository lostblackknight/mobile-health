package io.github.lostblackknight.model.entity.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 医院
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ApiModel(description = "医院")
public class Hospital implements Serializable {

    @ApiModelProperty(value = "医院编号")
    private String hospitalCode;

    @ApiModelProperty(value = "医院类型")
    private String hospitalType;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "市code")
    private String cityCode;

    @ApiModelProperty(value = "区code")
    private String districtCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "医院logo")
    private String logo;

    @ApiModelProperty(value = "医院简介")
    private String intro;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "状态(0: 未上线 1: 已上线)")
    private Integer status;

    @ApiModelProperty(value = "预约规则")
    private BookingRule bookingRule;

    private static final long serialVersionUID = 1L;
}
