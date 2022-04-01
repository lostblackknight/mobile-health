package io.github.lostblackknight.model.entity.admin;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName t_dict
 */
@TableName(value ="t_dict")
@Data
public class Dict implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 字典的标签
     */
    private String dictLabel;

    /**
     * 字典的值
     */
    private String dictValue;

    /**
     * 字典的排序
     */
    private Long dictSort;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

    @TableField(exist = false)
    private List<Dict> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}