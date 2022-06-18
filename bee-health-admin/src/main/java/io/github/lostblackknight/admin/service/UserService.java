package io.github.lostblackknight.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.model.dto.PageDTO;
import io.github.lostblackknight.model.entity.admin.User;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2022-03-26 18:14:57
*/
public interface UserService extends IService<User> {

    User getUserById(Long id);

    PageDTO<User> getUsersByPage(Long pageNum, Long pageSize, User user);

    boolean createUser(User user, List<Long> roleIds);

    boolean updateUserPassword(Long id, String password);

    boolean modifyUser(User user, List<Long> roleIds);

    boolean removeUser(Long id);

    boolean removeBatchUser(List<Long> ids);

    boolean modifyUserStatusById(Long id, Integer status);

    boolean resetUserPasswordById(Long id, String password);

    long getUserCountByRoleId(Long id);
}
