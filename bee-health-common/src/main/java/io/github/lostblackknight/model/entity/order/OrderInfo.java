package io.github.lostblackknight.model.entity.order;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * @TableName order_info
 */
@TableName(value ="order_info")
@Data
public class OrderInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单交易号（对外业务号）
     */
    private String orderSn;

    /**
     * 医院编号
     */
    private String hospitalCode;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 科室编号
     */
    private String deptCode;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 医生会员id
     */
    private Long doctorMemberId;

    /**
     * 医生编码
     */
    private String doctorCode;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 医生职称
     */
    private String levelName;

    /**
     * 医院预约记录id
     */
    private String recordId;

    /**
     * 预约序号
     */
    private Integer number;

    /**
     * 排班编号
     */
    private String scheduleId;

    /**
     * 就诊日期
     */
    private Date reserveDate;

    /**
     * 就诊时间（0：上午 1：下午）
     */
    private Integer reserveTime;

    /**
     * 就诊人id
     */
    private Long patientId;

    /**
     * 就诊人名称
     */
    private String patientName;

    /**
     * 就诊人手机
     */
    private String patientPhone;

    /**
     * 费用
     */
    private BigDecimal amount;

    /**
     * 退号时间
     */
    private Date quitTime;

    /**
     * 订单状态
     */
    private Integer orderStatus;

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