package io.github.lostblackknight.message.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class MessageModel {
    private Integer action;
    private Object msg;
    private Object extend;
}
