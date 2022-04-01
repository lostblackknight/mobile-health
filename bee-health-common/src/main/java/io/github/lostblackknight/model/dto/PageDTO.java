package io.github.lostblackknight.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
public class PageDTO<T> {
    private Long pageNum;
    private Long pageSize;
    private Long total;
    private List<T> records;
}
