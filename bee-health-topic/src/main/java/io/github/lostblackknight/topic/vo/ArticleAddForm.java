package io.github.lostblackknight.topic.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ArticleAddForm {

    /**
     * 标题
     */
    private String title;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 封面
     */
    private String cover;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 阅读数量
     */
    private Long readCount;

    /**
     * 点赞数量
     */
    private Long likeCount;

    /**
     * 收藏数量
     */
    private Long collectionCount;
}
