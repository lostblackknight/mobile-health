package io.github.lostblackknight.message.controller;

import io.github.lostblackknight.message.config.MemberClient;
import io.github.lostblackknight.message.entity.Chat;
import io.github.lostblackknight.message.service.ChatService;
import io.github.lostblackknight.message.support.TokenInfoContextHolder;
import io.github.lostblackknight.message.vo.ChatAddForm;
import io.github.lostblackknight.model.dto.ChatDTO;
import io.github.lostblackknight.model.dto.TokenInfoDTO;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chats")
    public CommonResult<?> createChat(@RequestBody ChatAddForm form) {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long memberId = dto.getUid();
        final boolean flag = chatService.createChat(memberId, form);
        return flag ? CommonResult.success() : CommonResult.fail();
    }

    @GetMapping("/chats/first/{anotherId}")
    public CommonResult<?> isFirstChat(@PathVariable Long anotherId) {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        final Long memberId = dto.getUid();
        boolean flag = chatService.isFirstChat(anotherId, memberId);
        return CommonResult.success(Map.of("isFirst", flag));
    }

    @GetMapping("/chats/list/memberId")
    public CommonResult<?> getChatList() {
        final TokenInfoDTO dto = TokenInfoContextHolder.get();
        List<ChatDTO> chats = chatService.getChatListByMemberId(dto.getUid());
        return CommonResult.success(chats);
    }
}
