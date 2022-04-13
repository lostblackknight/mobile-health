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
@Document(indexName = "dept")
public class DeptESModel {
    /**
     * 科室编码
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String deptCode;

    /**
     * 医院的编码
     */
    @Field(type = FieldType.Keyword)
    private String hospitalCode;

    /**
     * 医院的名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String hospitalName;

    /**
     * 省
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String province;

    /**
     * 市
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String city;

    /**
     * 科室的名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String deptName;

    /**
     * 科室的介绍
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String intro;

    /**
     * 科室分类的编码
     */
    @Field(type = FieldType.Keyword)
    private String classCode;

    /**
     * 科室分类的名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String className;
}
