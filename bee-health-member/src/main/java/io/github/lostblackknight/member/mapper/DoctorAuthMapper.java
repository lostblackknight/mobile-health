package io.github.lostblackknight.member.mapper;

import io.github.lostblackknight.member.vo.AuthQueryForm;
import io.github.lostblackknight.model.entity.member.DoctorAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_doctor_auth】的数据库操作Mapper
* @createDate 2022-04-25 18:52:34
* @Entity io.github.lostblackknight.model.entity.member.DoctorAuth
*/
public interface DoctorAuthMapper extends BaseMapper<DoctorAuth> {

    List<DoctorAuth> getAuthList(@Param("from") long from, @Param("to") long to, @Param("form") AuthQueryForm form);

    long getAuthCount(@Param("form") AuthQueryForm form);
}




