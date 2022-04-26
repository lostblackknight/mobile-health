package io.github.lostblackknight.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@Document(indexName = "article")
public class ArticleESModel {
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**
     * 作者名称
     */
    @Field(type = FieldType.Keyword)
    private String author;

    /**
     * 用户ID
     */
    @Field(type = FieldType.Long)
    private Long uid;

    /**
     * 作者的头像
     */
    @Field(type = FieldType.Keyword)
    private String avatar;

    @Field(type = FieldType.Keyword)
    private String role;

    /**
     * 封面
     */
    @Field(type = FieldType.Keyword)
    private String cover;

    /**
     * 分类ID
     */
    @Field(type = FieldType.Long)
    private Long categoryId;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    /**
     * 文章内容
     */
    @Field(type = FieldType.Keyword)
    private String content;

    /**
     * 阅读数量
     */
    @Field(type = FieldType.Long)
    private Long readCount;

    /**
     * 点赞数量
     */
    @Field(type = FieldType.Long)
    private Long likeCount;

    /**
     * 收藏数量
     */
    @Field(type = FieldType.Long)
    private Long collectionCount;
}
