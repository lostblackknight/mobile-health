package io.github.lostblackknight.model.entity.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 科室
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ApiModel(description = "科室")
public class Department implements Serializable {

    @ApiModelProperty(value = "医院编号")
    private String hospitalCode;

    @ApiModelProperty(value = "科室编号")
    private String departmentCode;

    @ApiModelProperty(value = "科室名称")
    private String departmentName;

    @ApiModelProperty(value = "科室简介")
    private String intro;

    private static final long serialVersionUID = 1L;
}
