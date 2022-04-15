package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DeptESDTO {
    private String id;
    private String text;
    private List<DeptESDTO> children;
}
