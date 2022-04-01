package io.github.lostblackknight.admin.controller.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class UserForm {
    private Long id;
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String email;
    private Integer status;
}
