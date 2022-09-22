package com.example.demo.controler;

import com.example.demo.entity.UserEntity;
import com.example.demo.exeption.UserAlreadyExistException;
import com.example.demo.exeption.UserNotFoundExeption;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try{
            userService.registration(user);
            return  ResponseEntity.ok("Регистрация произошла успешно");
        } catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка регистрации");
        }
    }

    @GetMapping
    public ResponseEntity getOneUsers(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundExeption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
