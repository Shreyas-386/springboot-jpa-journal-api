package com.pict.journalApp.service;

import com.pict.journalApp.entity.User;
import com.pict.journalApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllEntries() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getByUsername(String name) {
        return repository.findByName(name).orElse(null);
    }

    public void enterUser(User obj) {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        obj.setRoles(Arrays.asList("USER"));
        repository.save(obj);
    }

    public void saveAdmin(User obj) {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        obj.setRoles(Arrays.asList("ADMIN", "USER"));
        repository.save(obj);
    }

    public void updateUser(User obj) {
        repository.save(obj);
    }

    @Transactional
    public void deleteUser(String username) {
        repository.deleteByName(username);
    }
}
