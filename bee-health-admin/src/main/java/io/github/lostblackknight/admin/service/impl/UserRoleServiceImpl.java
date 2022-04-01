package io.github.lostblackknight.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.admin.mapper.UserRoleMapper;
import io.github.lostblackknight.admin.service.UserRoleService;
import io.github.lostblackknight.model.entity.admin.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author chensixiang
* @description 针对表【t_user_role(用户角色关联关系表)】的数据库操作Service实现
* @createDate 2022-03-26 18:15:13
*/
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




