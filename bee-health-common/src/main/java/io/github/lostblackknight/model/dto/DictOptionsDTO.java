package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DictOptionsDTO {
    private String label;
    private Long id;
    private List<DictOptionsDTO> children;
}
