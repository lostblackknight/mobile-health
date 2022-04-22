package io.github.lostblackknight.message.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.constant.MessageConstant;
import io.github.lostblackknight.message.entity.Message;
import io.github.lostblackknight.message.service.MessageService;
import io.github.lostblackknight.message.vo.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@ServerEndpoint("/ws/chat/{uid}")
@Component
@Slf4j
public class WebSocketServer {

    public static final Map<Long, Session> sessionMap = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        log.info("ws 连接已建立，建立的客户端UID为：{}", uid);
        // 建立连接保存用户与Session的关联关系
        sessionMap.put(Long.valueOf(uid), session);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uid") String uid) throws JsonProcessingException {
        // 接受客户端的消息
        log.info("收到的客户端{}的消息为：{}", uid, message);
        final ObjectMapper mapper = new ObjectMapper();
        final MessageService messageService = SpringUtils.getBean("messageService", MessageService.class);
        // 根据消息的类型进行不同的操作
        final MessageModel model = mapper.readValue(message, MessageModel.class);

        if (Objects.equals(model.getAction(), MessageConstant.CHAT_MESSAGE)) {
            // 1. 用户聊天消息
            // 1.1 存储聊天消息，标记为未读
            final Map<String, Object> modelMsg = (Map<String, Object>) model.getMsg();

            Message msg = new Message();
            msg.setFromId(Long.valueOf((Integer)modelMsg.get("fromId")));
            msg.setToId(Long.valueOf((Integer)modelMsg.get("toId")));
            msg.setContent((String) modelMsg.get("content"));
            msg.setType((Integer) modelMsg.get("type"));
            msg.setSendTime(new Date());
            msg.setStatus(0);
            messageService.save(msg);
            // 1.2 推送聊天消息给接受者（接受者可能离线）
            final Session toSession = sessionMap.get(msg.getToId());
            final Session formSession = sessionMap.get(msg.getFromId());
            if (formSession != null) {
                // 在线
                try {
                    log.info("在线");
                    formSession.getBasicRemote().sendText(mapper.writeValueAsString(msg));
                } catch (IOException e) {
//                    e.printStackTrace();
                    // 推送失败，离线 TODO: 推送消息
                    log.info("离线2");
                }
            } else {
                // 离线 TODO: 推送消息
                log.info("离线1");
            }
            if (toSession != null) {
                // 在线
                try {
                    log.info("在线");
                    toSession.getBasicRemote().sendText(mapper.writeValueAsString(msg));
                } catch (IOException e) {
//                    e.printStackTrace();
                    // 推送失败，离线 TODO: 推送消息
                    log.info("离线2");
                }
            } else {
                // 离线 TODO: 推送消息
                log.info("离线1");
            }
        } else if (Objects.equals(model.getAction(), MessageConstant.SIGN_MESSAGE)) {
            // - 用户收到消息后给后端发个签收的消息，签收
            // - 点击标记为已读，签收
            // - 用户离线，再次上线，进入聊天室时，获取未签收的消息，再发送为签收的消息到后台，签收
            // 2. 签收的消息
            // 获取签收的消息的id，修改数据库的状态
            final Map<String, Object> modelMsg = (Map<String, Object>) model.getMsg();
            final Long fromId = Long.valueOf((Integer) modelMsg.get("fromId"));
            final Long toId = Long.valueOf((Integer) modelMsg.get("toId"));
            List<Integer> msgIds = (List<Integer>) modelMsg.get("ids");

            if (sessionMap.get(toId) != null) {
                final List<Long> collect = msgIds.stream().map(Integer::longValue).collect(Collectors.toList());
                messageService.sign(collect);
            }
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("uid") String uid) {
        // 关闭连接删除用户与Session的关联关系
        log.info("ws 连接已关闭，关闭的客户端UID为：{}", uid);
        sessionMap.remove(Long.valueOf(uid));
    }
}
