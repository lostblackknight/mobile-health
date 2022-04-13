package io.github.lostblackknight.model.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 会员类型关联关系表
 * @TableName t_member_role
 */
@TableName(value ="t_member_role")
@Data
public class MemberRole implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 角色ID
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}