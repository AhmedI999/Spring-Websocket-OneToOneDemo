package com.ahmed.websocketadvanceddemo.controller;

import com.ahmed.websocketadvanceddemo.service.UserService;
import com.ahmed.websocketadvanceddemo.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user){
        service.saveUser(user);
        return user;
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user){
        service.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
