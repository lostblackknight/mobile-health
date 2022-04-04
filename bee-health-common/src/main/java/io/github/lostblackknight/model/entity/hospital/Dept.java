package io.github.lostblackknight.model.entity.hospital;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName t_dept
 */
@TableName(value ="t_dept")
@Data
public class Dept implements Serializable {
    /**
     * 科室的编码
     */
    @TableId
    private String deptCode;

    /**
     * 医院的编码
     */
    private String hospitalCode;

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 科室的名称
     */
    private String deptName;

    /**
     * 科室的介绍
     */
    private String intro;

    /**
     * 科室分类的编码
     */
    private String classCode;

    /**
     * 科室分类的名称
     */
    private String className;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}