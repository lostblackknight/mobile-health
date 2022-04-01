package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.admin.mapper.UserLoginLogMapper;
import io.github.lostblackknight.admin.service.UserLoginLogService;
import io.github.lostblackknight.model.entity.admin.UserLoginLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author chensixiang
* @description 针对表【t_user_login_log】的数据库操作Service实现
* @createDate 2022-03-26 18:15:17
*/
@Service
@Transactional
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog>
    implements UserLoginLogService{

}




