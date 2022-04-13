package io.github.lostblackknight.model.entity.member;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.lostblackknight.model.entity.admin.Role;
import lombok.Data;

/**
 * 会员信息表
 * @TableName t_member
 */
@TableName(value ="t_member")
@Data
public class Member implements Serializable {
    /**
     * 会员ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）
     */
    private Integer status;

    /**
     * 身份证号
     */
    private String certificatesNumber;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}