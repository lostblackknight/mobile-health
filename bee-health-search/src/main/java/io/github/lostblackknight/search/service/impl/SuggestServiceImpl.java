package io.github.lostblackknight.search.service.impl;

import io.github.lostblackknight.search.entity.SuggestESModel;
import io.github.lostblackknight.search.repository.SuggestRepository;
import io.github.lostblackknight.search.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class SuggestServiceImpl implements SuggestService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final SuggestRepository suggestRepository;

    @Override
    public List<String> getSuggestByKeyword(String keyword) {
        final CompletionSuggestionBuilder suggestion = SuggestBuilders.completionSuggestion("suggest").prefix(keyword).size(10);

        final SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("my_suggestion", suggestion);

        final SearchResponse response = elasticsearchRestTemplate.suggest(suggestBuilder, SuggestESModel.class);

        final List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> entries = response.getSuggest().getSuggestion("my_suggestion").getEntries();
        final ArrayList<String> result = new ArrayList<>();
        entries.stream().map(options -> options.getOptions().stream()
                .map(option -> option.getText().toString()).collect(Collectors.toList())
        ).forEach(result::addAll);
        return result;
    }

    @Override
    public void saveKeyword(SuggestESModel model) {
        suggestRepository.save(model);
    }

    @Override
    public void saveKeywords(List<SuggestESModel> models) {
        suggestRepository.saveAll(models);
    }

    @Override
    public void removeById(String id) {
        suggestRepository.deleteById(id);
    }
}
