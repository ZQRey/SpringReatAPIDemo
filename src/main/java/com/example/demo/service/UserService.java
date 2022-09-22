package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exeption.UserAlreadyExistException;
import com.example.demo.exeption.UserNotFoundExeption;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("Пользователь существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundExeption {
        UserEntity user = userRepo.findById(id).get();
        if (user == null){
            throw new UserNotFoundExeption("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}
