package com.pict.journalApp.controller;

import com.pict.journalApp.entity.User;
import com.pict.journalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger log =
            LoggerFactory.getLogger(UserController.class);

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
        System.out.println("Controller hit");
        log.info("Inserting user");
        try{
            service.enterUser(obj);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e) {
            log.error("Failure due to wrong input : {}", String.valueOf(e));
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
