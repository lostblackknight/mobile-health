package io.github.lostblackknight.model.entity.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@ApiModel(description = "聊天消息")
public class Message implements Serializable {

    @ApiModelProperty(value = "发送者id")
    private String sendId;

    @ApiModelProperty(value = "接受者id")
    private String acceptId;

    @ApiModelProperty(value = "消息id(自增长)")
    private String msgId;

    @ApiModelProperty(value = "消息的类型")
    private String msgType;

    @ApiModelProperty(value = "消息的内容")
    private String content;

    @ApiModelProperty(value = "状态(0: 未读 1: 已读)")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "发送消息的时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
