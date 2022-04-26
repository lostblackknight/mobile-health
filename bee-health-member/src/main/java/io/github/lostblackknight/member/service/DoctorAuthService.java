package io.github.lostblackknight.member.service;

import io.github.lostblackknight.member.vo.AuthQueryForm;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.member.DoctorAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chensixiang
* @description 针对表【t_doctor_auth】的数据库操作Service
* @createDate 2022-04-25 18:52:34
*/
public interface DoctorAuthService extends IService<DoctorAuth> {

    boolean authToDoctor(DoctorAuth doctorAuth);

    PageDTO<DoctorAuth> getAuthList(Long pageNum, Long pageSize, AuthQueryForm form);

    boolean unAccess(Long id);

    boolean access(Long id);
}
