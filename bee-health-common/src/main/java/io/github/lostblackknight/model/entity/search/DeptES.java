package io.github.lostblackknight.model.entity.search;

import lombok.Data;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class DeptES {
    /**
     * 科室编码
     */
    private String deptCode;

    /**
     * 医院的编码
     */
    private String hospitalCode;

    /**
     * 医院的名称
     */
    private String hospitalName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 科室的名称
     */
    private String deptName;

    /**
     * 科室的介绍
     */
    private String intro;

    /**
     * 科室分类的编码
     */
    private String classCode;

    /**
     * 科室分类的名称
     */
    private String className;
}
