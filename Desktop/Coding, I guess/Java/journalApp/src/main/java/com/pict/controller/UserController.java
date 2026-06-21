package com.pict.controller;

import com.pict.entity.User;
import com.pict.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserId(@PathVariable int id) {
        if(service.getUserById(id) != null) return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User obj) {
        try{
            service.enterUser(obj);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User obj) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = service.getByUsername(username);
        if(user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.enterUser(obj);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public void deleteUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.deleteUser(username);
    }
}
