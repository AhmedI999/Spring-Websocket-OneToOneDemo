package com.ahmed.websocketadvanceddemo.model.chat;

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
public class ChatMessage {
    @Id
    String id;
    String chatId;
    String senderId;
    String recipientId;
    String content;
    Data timestamp;
}
