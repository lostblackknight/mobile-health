package io.github.lostblackknight.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.completion.Completion;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@Document(indexName = "suggest")
public class SuggestESModel {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", maxInputLength = 100)
    private Completion suggest;
}
