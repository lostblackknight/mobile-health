package io.github.lostblackknight.model.entity.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_message
 */
@TableName(value ="t_message")
@Data
public class Message implements Serializable {
    /**
     * 消息ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发送者ID
     */
    private Long fromId;

    /**
     * 接受者ID
     */
    private Long toId;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 消息的类型
     */
    private Integer type;

    /**
     * 消息发送的时间
     */
    private Date sendTime;

    /**
     * 消息的状态(1已读 0未读)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}