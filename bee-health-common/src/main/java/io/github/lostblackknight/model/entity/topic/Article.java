package io.github.lostblackknight.model.entity.topic;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_article
 */
@TableName(value ="t_article")
@Data
public class Article implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
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
    private String role;

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

    @TableField(exist = false)
    private Category category;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}