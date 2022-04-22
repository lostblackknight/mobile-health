package io.github.lostblackknight.member.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class MemberInfoVO {
    private List<String> roles;
    private String username;
    private String nickName;
    private String avatar;
    private Integer status;
    private Long uid;
}
