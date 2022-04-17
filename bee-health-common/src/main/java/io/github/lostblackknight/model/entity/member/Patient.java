package io.github.lostblackknight.model.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 就诊人表
 * @TableName t_patient
 */
@TableName(value ="t_patient")
@Data
public class Patient implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String certificatesNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;

    /**
     * 手机
     */
    private String phone;

    /**
     * 联系人姓名
     */
    private String contactsName;

    /**
     * 联系人证件号
     */
    private String contactsCertificatesNo;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 状态（0：默认 1：已认证）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}