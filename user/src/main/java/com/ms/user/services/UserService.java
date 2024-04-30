package com.ms.user.services;

import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepositorie;

import jakarta.transaction.Transactional;

@Service
public class UserService {


    final UserProducer userProducer;
    final UserRepositorie userRepositorie;

    public UserService(UserRepositorie userRepositorie, UserProducer userProducer) {
        this.userRepositorie = userRepositorie;
        this.userProducer=userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        userModel = userRepositorie.save(userModel);
        userProducer.publishMessageEmail(userModel);

        return userModel;
    }
}
