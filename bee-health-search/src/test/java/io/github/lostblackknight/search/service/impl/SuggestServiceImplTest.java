package io.github.lostblackknight.search.service.impl;

import io.github.lostblackknight.search.service.SuggestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class SuggestServiceImplTest {

    @Autowired
    SuggestService suggestService;

    @Test
    void getSuggestByKeyword() {
        System.out.println(suggestService.getSuggestByKeyword("小儿"));
    }
}