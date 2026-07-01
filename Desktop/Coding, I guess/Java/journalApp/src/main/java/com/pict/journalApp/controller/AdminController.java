package com.pict.journalApp.controller;

import com.pict.journalApp.cache.AppCache;
import com.pict.journalApp.entity.User;
import com.pict.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> entries = userService.getAllEntries();
        if(entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveAdmin(@RequestBody User user) {
        if(userService.getByUsername(user.getName()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Autowired
    private AppCache appCache;

    @GetMapping("/clear-app-cache")
    public ResponseEntity<?> reInitializeCache() {
        try{
            appCache.init();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
