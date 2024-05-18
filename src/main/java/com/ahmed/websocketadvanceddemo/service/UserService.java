package com.ahmed.websocketadvanceddemo.service;

import com.ahmed.websocketadvanceddemo.repository.UserRepository;
import com.ahmed.websocketadvanceddemo.model.Status;
import com.ahmed.websocketadvanceddemo.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }
    public void disconnect(User user){
        var storedUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if (storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }
    public List<User> findConnectedUsers(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
