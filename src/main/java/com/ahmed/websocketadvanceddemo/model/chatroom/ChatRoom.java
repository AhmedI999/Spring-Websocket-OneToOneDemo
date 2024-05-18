package com.ahmed.websocketadvanceddemo.model.chatroom;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRoom {
    @Id
    String id;
    String chatId;
    String senderId;
    String recipientId;
}
