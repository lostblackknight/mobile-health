package io.github.lostblackknight.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class UserAddForm {
    private String username;
    private String password;
    private String gender;
    private String avatar;
    private String phone;
    private String email;
    private Integer status;
    private List<Long> roleIds;
}
