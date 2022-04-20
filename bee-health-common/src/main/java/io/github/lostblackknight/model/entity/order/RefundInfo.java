package io.github.lostblackknight.model.entity.order;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 退款信息表
 * @TableName refund_info
 */
@TableName(value ="refund_info")
@Data
public class RefundInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 对外业务编号
     */
    private String orderSn;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 支付类型（微信 支付宝）
     */
    private Integer paymentType;

    /**
     * 交易编号
     */
    private String tradeNo;

    /**
     * 退款金额
     */
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 退款状态
     */
    private Integer refundStatus;

    /**
     * 回调信息
     */
    private String callbackContent;

    /**
     * 
     */
    private Date callbackTime;

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

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}