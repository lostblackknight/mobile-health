package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class TokenInfoDTO {
    private Long uid;
    private String principal;
    private List<String> roles;
}
