package io.github.lostblackknight.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.github.lostblackknight.message.config.MemberClient;
import io.github.lostblackknight.message.entity.Chat;
import io.github.lostblackknight.message.service.ChatService;
import io.github.lostblackknight.message.mapper.ChatMapper;
import io.github.lostblackknight.message.vo.ChatAddForm;
import io.github.lostblackknight.model.dto.ChatDTO;
import io.github.lostblackknight.model.entity.member.Member;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chensixiang
 * @description 针对表【t_chat】的数据库操作Service实现
 * @createDate 2022-04-21 14:04:58
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
        implements ChatService {

    private final MemberClient memberClient;

    @Override
    public boolean createChat(Long memberId, ChatAddForm form) {
        final Chat chat = new Chat();
        chat.setMemberId(memberId);
        chat.setAnotherId(form.getAnotherId());
        final int insert = baseMapper.insert(chat);
        return SqlHelper.retBool(insert);
    }

    @Override
    public boolean isFirstChat(Long anotherId, Long memberId) {
        final Long count = baseMapper.selectCount(new QueryWrapper<Chat>().eq("member_id", memberId).eq("another_id", anotherId));
        final Long count1 = baseMapper.selectCount(new QueryWrapper<Chat>().eq("member_id", anotherId).eq("another_id", memberId));
        return count + count1 == 0;
    }

    @Override
    public List<ChatDTO> getChatListByMemberId(Long uid) {

        List<ChatDTO> chatDTOS = new ArrayList<>();
        final List<Chat> chats = baseMapper.selectList(new QueryWrapper<Chat>().eq("member_id", uid));

        final List<ChatDTO> collect = chats.stream().map(chat -> {
            final Long id = chat.getAnotherId();
            final CommonResult<Member> result = memberClient.getMemberById(id);
            final Member data = result.getData();
            final ChatDTO chatDTO = new ChatDTO();
            chatDTO.setMemberId(uid);
            chatDTO.setAnotherId(data.getId());
            chatDTO.setNickName(data.getRealName());
            chatDTO.setPhone(data.getPhone());
            chatDTO.setAvatar(data.getAvatar());
            return chatDTO;
        }).collect(Collectors.toList());

        chatDTOS.addAll(collect);

        final List<Chat> chats1 = baseMapper.selectList(new QueryWrapper<Chat>().eq("another_id", uid));

        final List<ChatDTO> collect1 = chats1.stream().map(chat -> {
            final Long id = chat.getMemberId();
            final CommonResult<Member> result = memberClient.getMemberById(id);
            final Member data = result.getData();
            final ChatDTO chatDTO = new ChatDTO();
            chatDTO.setMemberId(uid);
            chatDTO.setAnotherId(data.getId());
            chatDTO.setNickName(data.getNickName());
            chatDTO.setPhone(data.getPhone());
            chatDTO.setAvatar(data.getAvatar());
            return chatDTO;
        }).collect(Collectors.toList());

        chatDTOS.addAll(collect1);

        return chatDTOS.stream().distinct().collect(Collectors.toList());
    }
}




