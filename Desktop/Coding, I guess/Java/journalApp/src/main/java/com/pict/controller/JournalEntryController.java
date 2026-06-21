package com.pict.controller;

import com.pict.entity.JournalEntry;
import com.pict.entity.User;
import com.pict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.pict.service.JournalEntryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;
    @Autowired
    private UserService user_service;

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return service.getallentries();
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = user_service.getByUsername(username);
        if(user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.getByUser(user),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = user_service.getByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId() == id).toList();
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = Optional.ofNullable(service.getbyid(id));
            if(journalEntry.isPresent()) return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry obj) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = user_service.getByUsername(username);
            if(user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            obj.setUser(user);
            service.insertentry(obj);
            return new ResponseEntity<>(obj,HttpStatus.CREATED);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable int id, @RequestBody JournalEntry obj) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = user_service.getByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId() == id).toList();
        if(!collect.isEmpty()) {
            JournalEntry journalEntry = service.getbyid(id);
            if(journalEntry != null) {
                service.updateEntry(id, obj);
                return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.deleteentry(id,username);
    }
}
