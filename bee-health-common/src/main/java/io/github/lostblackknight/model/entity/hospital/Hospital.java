package io.github.lostblackknight.model.entity.hospital;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName t_hospital
 */
@TableName(value ="t_hospital")
@Data
public class Hospital implements Serializable {
    /**
     * 医院的编码
     */
    @TableId
    private String hospitalCode;

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * logo
     */
    private String logo;

    /**
     * 医院的联系电话
     */
    private String telephone;

    /**
     * 医院的状态
     */
    private Integer status;

    /**
     * 医院的等级
     */
    private String levelName;

    /**
     * 医院的类型
     */
    private String className;

    /**
     * 医院的路线
     */
    private String route;

    /**
     * 简介
     */
    private String intro;

    /**
     * 预约规则
     */
    private String rules;

    /**
     * 医院的相关图片
     */
    private String images;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}