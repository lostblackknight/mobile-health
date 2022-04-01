package io.github.lostblackknight.admin.vo;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class HospitalClientDetailAddForm {

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 医院的API基础路径
     */
    private String apiUrl;

    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 联系人电话
     */
    private String contactsPhone;

    /**
     * 状态
     */
    private Integer status;
}
