package io.github.lostblackknight.admin.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.entity.admin.Dict;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SpringBootTest
class DictDataServiceTest {

    @Autowired
    private DictService dictService;


    /**
     * 添加区域列表数据
     *
     * @throws IOException
     */
    @Test
    void test31() throws IOException {
        final Dict dict1 = new Dict();
        dict1.setId(1L);
        dict1.setParentId(0L);
        dict1.setDictLabel("区域列表");
        dict1.setDictValue("area_list");
        dict1.setDictSort(1L);
        dict1.setCreateTime(new Date());
        dict1.setCreateBy("admin");

        dictService.save(dict1);

        final FileInputStream is = new FileInputStream("src/test/resources/area_list.json");

        System.out.println(is);
        final ObjectMapper mapper = new ObjectMapper();
        final List<Area> areas = mapper.readValue(is, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        long id = 2;
        long sort = 1;

        for (Area area : areas) {
            final Dict dict = new Dict();
            long parentId = id;
            dict.setId(id);
            dict.setParentId(1L);
            dict.setDictLabel(area.getArea_name());
            dict.setDictValue(area.getArea_id());
            dict.setDictSort(sort);
            dict.setCreateTime(new Date());
            dict.setCreateBy("admin");
            dictService.save(dict);
            id++;
            long sort2 = 1;
            for (Area child : area.getChildren()) {
                final Dict dict2 = new Dict();
                dict2.setId(id);
                dict2.setParentId(parentId);
                dict2.setDictLabel(child.getArea_name());
                dict2.setDictValue(child.getArea_id());
                dict2.setDictSort(sort2);
                dict2.setCreateTime(new Date());
                dict2.setCreateBy("admin");
                dictService.save(dict2);
                sort2++;
                id++;
            }
            sort++;
        }
    }

    @Test
    void getDict() {
    }

    @Test
    void getDictOptions() {
    }

    @Test
    void removeDictById() {
    }

    @Test
    void removeBatchDictByIds() {
    }

    @Test
    void getAreaList() {
        System.out.println(dictService.getAreaList());
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Area {
        String area_id;
        String area_name;
        List<Area> children;
    }
}