package com.example.demo.exeption;

public class UserNotFoundExeption extends Exception{

    public UserNotFoundExeption(String message) {
        super(message);
    }
}
