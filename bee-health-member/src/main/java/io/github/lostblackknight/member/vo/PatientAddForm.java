package io.github.lostblackknight.member.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class PatientAddForm {

    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String certificatesNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;

    /**
     * 手机
     */
    private String phone;

    /**
     * 联系人姓名
     */
    private String contactsName;

    /**
     * 联系人证件号
     */
    private String contactsCertificatesNo;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 状态（0：默认 1：已认证）
     */
    private Integer status;
}
