package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class AreaDTO {
    private String label;
    private String value;
    private List<AreaDTO> children;
}
