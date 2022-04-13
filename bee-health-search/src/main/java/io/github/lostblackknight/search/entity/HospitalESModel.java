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
@Document(indexName = "hospital")
public class HospitalESModel {
    /**
     * 医院编码
     */
    @Id
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
     * 详细地址
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String address;

    /**
     * logo
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String logo;

    /**
     * 医院的联系电话
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String telephone;

    /**
     * 医院的等级
     */
    @Field(type = FieldType.Keyword)
    private String levelName;

    /**
     * 医院的类型
     */
    @Field(type = FieldType.Keyword)
    private String className;

    /**
     * 医院的路线
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String route;

    /**
     * 简介
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String intro;

    /**
     * 预约规则
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String rules;

    /**
     * 医院的相关图片
     */
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String images;
}
