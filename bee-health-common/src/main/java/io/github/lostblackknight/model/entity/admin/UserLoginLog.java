package io.github.lostblackknight.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_user_login_log
 */
@TableName(value ="t_user_login_log")
@Data
public class UserLoginLog implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录的IP
     */
    private String ip;

    /**
     * 登录时间
     */
    private Date loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}