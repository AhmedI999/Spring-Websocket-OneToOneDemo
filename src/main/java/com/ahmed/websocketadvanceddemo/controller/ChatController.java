package com.ahmed.websocketadvanceddemo.controller;

import com.ahmed.websocketadvanceddemo.model.chat.ChatMessage;
import com.ahmed.websocketadvanceddemo.model.chat.ChatNotification;
import com.ahmed.websocketadvanceddemo.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatMessageService chatMessageService;
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMsg = chatMessageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getContent()
                ));

    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable("senderId") String senderId,
                                                              @PathVariable("recipientId") String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
