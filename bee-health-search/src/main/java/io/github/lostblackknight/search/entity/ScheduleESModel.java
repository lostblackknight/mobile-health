package io.github.lostblackknight.search.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@Document(indexName = "schedule")
public class ScheduleESModel {
    /**
     * 排班的id
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String scheduleId;

    /**
     * 医院的编码
     */
    @Field(type = FieldType.Keyword)
    private String hospitalCode;

    /**
     * 医院的名称
     */
    @Field(type = FieldType.Keyword)
    private String hospitalName;

    /**
     * 科室的编码
     */
    @Field(type = FieldType.Keyword)
    private String deptCode;

    /**
     * 科室的名称
     */
    @Field(type = FieldType.Keyword)
    private String deptName;

    /**
     * 医生的名称
     */
    @Field(type = FieldType.Keyword)
    private String doctorName;

    @Field(type = FieldType.Keyword)
    private String doctorCode;

    @Field(type = FieldType.Integer)
    private Long memberId;

    /**
     * 医生的专长
     */
    @Field(type = FieldType.Keyword)
    private String expert;

    /**
     * 专治的病情
     */
    @Field(type = FieldType.Keyword)
    private String illNameList;

    /**
     * 医生的头像
     */
    @Field(type = FieldType.Keyword)
    private String icon;

    /**
     * 医生的职称
     */
    @Field(type = FieldType.Keyword)
    private String levelName;

    /**
     * 排班日期
     */
    @Field(type = FieldType.Keyword)
    private String date;

    /**
     * 排班星期
     */
    @Field(type = FieldType.Keyword)
    private String week;

    /**
     * 排班的时间(am, pm, null)
     */
    @Field(type = FieldType.Keyword)
    private String timeType;

    /**
     * 挂号费
     */
    @Field(type = FieldType.Keyword)
    private BigDecimal amount;

    /**
     * 预约状态
     */
    @Field(type = FieldType.Integer)
    private Integer yuYueState;

    /**
     * 最大预约数
     */
    @Field(type = FieldType.Integer)
    private Integer yuYueMax;

    /**
     * 已预约的数量
     */
    @Field(type = FieldType.Integer)
    private Integer yuYueNum;
}
