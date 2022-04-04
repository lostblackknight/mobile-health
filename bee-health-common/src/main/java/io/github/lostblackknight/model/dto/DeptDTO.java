package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DeptDTO {
    private String id;
    private String label;
    private List<DeptDTO> children;
}
