package io.github.lostblackknight.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.model.entity.admin.Role;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_role(角色表)】的数据库操作Service
* @createDate 2022-03-26 18:15:06
*/
public interface RoleService extends IService<Role> {

    List<Role> getRolesByUserId(Long id);

    Role getRoleByRoleTag(String tag);

    boolean removeRoleById(Long id);

    boolean removeBatchRoleById(List<Long> ids);
}
