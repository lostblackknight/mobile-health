package io.github.lostblackknight.message.service;

import io.github.lostblackknight.model.entity.message.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chensixiang
* @description 针对表【t_message】的数据库操作Service
* @createDate 2022-04-21 14:05:07
*/
public interface MessageService extends IService<Message> {

    void sign(List<Long> id);

    List<Message> getMessageList(String fromId, String toId);

    List<Message> getAllUnSignedMessage();

    List<Message> getUnsignedMessage(String fromId, String toId);

    Message getLastMessageDetail(String fromId, String toId);

    void allRead();
}
