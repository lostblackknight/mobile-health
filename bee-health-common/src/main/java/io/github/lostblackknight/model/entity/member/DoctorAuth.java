package io.github.lostblackknight.model.entity.member;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_doctor_auth
 */
@TableName(value ="t_doctor_auth")
@Data
public class DoctorAuth implements Serializable {

    /**
     * 会员ID
     */
    @TableId
    private Long memberId;

    /**
     * 证件照
     */
    private String certificates;

    /**
     * 状态
     */
    private Long status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private Member member;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}