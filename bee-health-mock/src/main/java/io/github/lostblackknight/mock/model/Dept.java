package io.github.lostblackknight.mock.model;

import lombok.Data;


@Data
public class Dept {
    /**
     * 科室的编码
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