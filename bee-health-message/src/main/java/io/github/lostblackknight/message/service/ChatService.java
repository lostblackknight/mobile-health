package io.github.lostblackknight.message.service;

import io.github.lostblackknight.model.entity.message.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lostblackknight.message.vo.ChatAddForm;
import io.github.lostblackknight.model.dto.ChatDTO;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_chat】的数据库操作Service
* @createDate 2022-04-21 14:04:58
*/
public interface ChatService extends IService<Chat> {

    boolean createChat(Long memberId, ChatAddForm form);

    boolean isFirstChat(Long anotherId, Long memberId);

    List<ChatDTO> getChatListByMemberId(Long uid);
}
