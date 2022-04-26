package io.github.lostblackknight.model.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ArticleSearchParam {
    private Long id;
    private String title;
    private String author;
    private Long uid;
    private Long categoryId;
    private String categoryName;
    private Integer pageNum = 1;
    private Integer pageSize = 5;
}
