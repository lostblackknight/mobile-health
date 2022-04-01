package io.github.lostblackknight.admin.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@Builder
public class UserInfoVO {
    private String name;
    private String avatar;
    private List<String> roles;
}
