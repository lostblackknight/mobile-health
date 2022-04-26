package io.github.lostblackknight.model.entity.search;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class ArticleES {

    private Long id;

    private String title;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 作者的头像
     */
    private String avatar;

    private String role;

    /**
     * 封面
     */
    private String cover;

    /**
     * 分类ID
     */
    private Long categoryId;

    private String categoryName;

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
