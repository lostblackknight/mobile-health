package io.github.lostblackknight.message.controller;

import io.github.lostblackknight.message.entity.Message;
import io.github.lostblackknight.message.service.MessageService;
import io.github.lostblackknight.model.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/messages/list/{fromId}/{toId}")
    public CommonResult<List<Message>> getMessageList(@PathVariable String fromId, @PathVariable String toId) {

        List<Message> list = messageService.getMessageList(fromId, toId);
        return CommonResult.success(list);
    }

    @GetMapping("/messages/unsigned/all")
    public CommonResult<?> getAllUnSignedMessage() {
        List<Message> messages = messageService.getAllUnSignedMessage();
        return CommonResult.success(messages);
    }

    @GetMapping("/messages/unsigned/{fromId}/{toId}")
    public CommonResult<?> getUnsignedMessage(@PathVariable String fromId, @PathVariable String toId) {
        List<Message> messageList = messageService.getUnsignedMessage(fromId, toId);
        return CommonResult.success(messageList);
    }

    @PutMapping("/messages/sign")
    public CommonResult<?> signMessage(@RequestBody List<Long> ids) {
        messageService.sign(ids);
        return CommonResult.success();
    }

    @GetMapping("/messages/all/read")
    public CommonResult<?> allRead() {
        messageService.allRead();
        return CommonResult.success();
    }

    @GetMapping("/messages/lasted/{fromId}/{toId}")
    public CommonResult<?> getLastMessageDetail(@PathVariable String fromId, @PathVariable String toId) {
        Message message = messageService.getLastMessageDetail(fromId, toId);
        return CommonResult.success(message);
    }
}
