package io.github.lostblackknight.admin.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DictEditForm {
    private Long id;
    private Long parentId;
    private String dictLabel;
    private String dictValue;
    private Long dictSort;
}
