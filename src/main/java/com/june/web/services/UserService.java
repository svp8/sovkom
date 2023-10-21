package com.june.web.services;

import com.june.web.models.User;
import com.june.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public ResponseEntity<?> register(User newuser) {
        if (newuser != null) {
            User user = userRepo.findByUsername(newuser.getUsername());
            if (user == null) {
                newuser.setPassword(newuser.getPassword());
                User created=userRepo.save(newuser);
                created.setPassword(null);
                return ResponseEntity.ok(created);
            }else{
                return ResponseEntity.badRequest()
                        .body("Username has been already taken");
            }
        }
        return ResponseEntity.badRequest()
                .body("Error in Sign up");
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(int id) {
        return userRepo.findById(id).orElse(null);
    }


    public ResponseEntity<?> login(User user) throws Exception {
        if (user != null) {
            User test = userRepo.findByUsername(user.getUsername());
            if (test != null) {
                if (user.getPassword().equals(test.getPassword())) {
                    return ResponseEntity.ok(test);
                }else {
                    return ResponseEntity.badRequest()
                            .body("Error in Log In");
                }
            }
        }

        return ResponseEntity.badRequest()
                .body("There is no such user");
    }

}
