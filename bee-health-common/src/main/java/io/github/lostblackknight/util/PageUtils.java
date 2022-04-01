package io.github.lostblackknight.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lostblackknight.model.dto.PageDTO;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public class PageUtils {

    public static <T> PageDTO<T> toPage(Page<T> page) {
        final List<T> records = page.getRecords();
        final long total = page.getTotal();
        final long size = page.getSize();
        final long current = page.getCurrent();
        final PageDTO<T> dto = new PageDTO<>();
        dto.setPageNum(current);
        dto.setPageSize(size);
        dto.setTotal(total);
        dto.setRecords(records);
        return dto;
    }
}
