package io.github.lostblackknight.admin.service;

import cn.hutool.log.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.model.entity.admin.Dict;
import io.github.lostblackknight.model.entity.admin.DictData;
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

    @Test
    void test11() throws IOException {
        final Dict dict1 = new Dict();
        dict1.setId(1L);
        dict1.setParentId(0L);
        dict1.setDictLabel("省");
        dict1.setDictValue("省");
        dict1.setDictSort(1L);
        dict1.setCreateTime(new Date());
        dict1.setCreateBy("admin");


        dictService.save(dict1);


        final Dict dict2 = new Dict();
        dict2.setId(2L);
        dict2.setParentId(0L);
        dict2.setDictLabel("市");
        dict2.setDictValue("市");
        dict2.setDictSort(2L);
        dict2.setCreateTime(new Date());
        dict2.setCreateBy("admin");


        dictService.save(dict2);

        final FileInputStream is = new FileInputStream("src/test/resources/p_list.json");
        final FileInputStream is1 = new FileInputStream("src/test/resources/c_list.json");

        final ObjectMapper mapper = new ObjectMapper();
        final List<Province> provinces = mapper.readValue(is, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        final List<City> cities = mapper.readValue(is1, new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        long sort = 1;
        long id = 3L;

        for (Province province : provinces) {
            final Dict dict = new Dict();
            dict.setId(id);
            dict.setParentId(1L);
            dict.setDictLabel(province.pname);
            dict.setDictValue(province.pid);
            dict.setDictSort(sort);
            dict.setCreateTime(new Date());
            dict.setCreateBy("admin");
            dictService.save(dict);
            sort++;
            id++;
        }

        sort = 1;
        for (City city : cities) {
            final Dict dict = new Dict();
            dict.setId(id);
            dict.setParentId(2L);
            dict.setDictLabel(city.cname);
            dict.setDictValue(city.cid);
            dict.setDictSort(sort);
            dict.setCreateTime(new Date());
            dict.setCreateBy("admin");
            dictService.save(dict);
            sort++;
            id++;
        }
    }


    @Test
    void test55() throws IOException {
    }

    @Test
    void getDict() throws JsonProcessingException {
        final List<Dict> dict = dictService.getDict("");
        System.out.println(new ObjectMapper().writeValueAsString(dict));
    }

    @Test
    void getDictOptions() {
        System.out.println(dictService.getDictOptions());
    }

    @Data
    static class Province {
        String pid;
        String pname;
    }

    @Data
    static class City {
        String cid;
        String cname;
    }
}