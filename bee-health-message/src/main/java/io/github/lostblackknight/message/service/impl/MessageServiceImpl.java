package io.github.lostblackknight.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.message.Message;
import io.github.lostblackknight.message.mapper.MessageMapper;
import io.github.lostblackknight.message.service.MessageService;
import io.github.lostblackknight.message.support.TokenInfoContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_message】的数据库操作Service实现
 * @createDate 2022-04-21 14:05:07
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Override
    public void sign(List<Long> id) {
        final List<Message> list = id.stream().map(i -> {
            final Message message = new Message();
            message.setId(i);
            message.setStatus(1);
            return message;
        }).collect(Collectors.toList());
        updateBatchById(list);
    }

    @Override
    public List<Message> getMessageList(String fromId, String toId) {
        final List<Message> messageList1 = baseMapper.selectList(new QueryWrapper<Message>().eq("from_id", fromId).eq("to_id", toId));
        final List<Message> messageList2 = baseMapper.selectList(new QueryWrapper<Message>().eq("from_id", toId).eq("to_id", fromId));
        final ArrayList<Message> messages = new ArrayList<>();
        messages.addAll(messageList1);
        messages.addAll(messageList2);
        return messages.stream().distinct().sorted((o1, o2) -> {
            if (o1.getId() - o2.getId() > 0) {
                return 1;
            } else if (o1.getId() - o2.getId() < 0) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Message> getAllUnSignedMessage() {
        return baseMapper.selectList(new QueryWrapper<Message>().eq("status", 0).eq("to_id", TokenInfoContextHolder.get().getUid()));
    }

    @Override
    public List<Message> getUnsignedMessage(String fromId, String toId) {
        return baseMapper.selectList(new QueryWrapper<Message>().eq("from_id", toId).eq("to_id", fromId).eq("status",0));
    }

    @Override
    public Message getLastMessageDetail(String fromId, String toId) {
        return this.getMessageList(fromId, toId).get(this.getMessageList(fromId, toId).size() - 1);
    }

    @Override
    public void allRead() {
        final List<Message> messageList = baseMapper.selectList(new QueryWrapper<Message>().eq("to_id", TokenInfoContextHolder.get().getUid()).eq("status", 0));
        final List<Message> collect = messageList.stream().peek(message -> message.setStatus(1)).collect(Collectors.toList());
        updateBatchById(collect);
    }
}




