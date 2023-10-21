package com.june.web.controllers;

import com.june.web.models.User;
import com.june.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController()
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id){
        User user=service.getById(id);
        if(user!=null){
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("No such user");
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
        return service.register(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws Exception {
        return service.login(user);
    }
    @GetMapping("/all")
    public List<User> getUsers(){
        return service.getAll();
    }

}
