package io.github.lostblackknight.model.entity.admin;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院客户端详情表
 *
 * @TableName t_hospital_client_detail
 */
@TableName(value = "t_hospital_client_detail")
@Data
public class HospitalClientDetail implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 医院的ID
     */
    private String hospitalId;

    /**
     * 医院的秘钥
     */
    private String hospitalSecret;

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 医院的API基础路径
     */
    private String apiUrl;

    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 联系人电话
     */
    private String contactsPhone;

    /**
     * 状态
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}