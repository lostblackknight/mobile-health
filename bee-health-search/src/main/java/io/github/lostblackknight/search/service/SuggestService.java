package io.github.lostblackknight.search.service;

import io.github.lostblackknight.search.entity.SuggestESModel;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public interface SuggestService {

    List<String> getSuggestByKeyword(String keyword);

    void saveKeyword(SuggestESModel model);

    void saveKeywords(List<SuggestESModel> models);
}
