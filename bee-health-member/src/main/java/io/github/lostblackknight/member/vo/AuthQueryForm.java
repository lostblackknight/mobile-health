package io.github.lostblackknight.member.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class AuthQueryForm {
    private String username;
    private String phone;
    private String nickName;
    private String realName;
    private Integer status;
}
