package com.ahmed.websocketadvanceddemo.repository;

import com.ahmed.websocketadvanceddemo.model.Status;
import com.ahmed.websocketadvanceddemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
