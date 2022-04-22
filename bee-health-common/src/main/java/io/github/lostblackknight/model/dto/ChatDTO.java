package io.github.lostblackknight.model.dto;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ChatDTO {

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 另一个会员ID
     */
    private Long anotherId;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;
}
