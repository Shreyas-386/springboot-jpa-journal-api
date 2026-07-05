package com.pict.journalApp.service;

import com.pict.journalApp.entity.User;
import com.pict.journalApp.repository.UserRepository;
import com.pict.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    public UserRepository repository;

    @ParameterizedTest
    @ValueSource(strings = {
            "Aman",
            "Amit",
            "Babu"
    })
    public void findbyUsername(String name) {
        assertNotNull(repository.findByName(name).orElse(null));
    }

    @Autowired
    public UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser() {
        List<User> users = userRepository.findByEmailIsNotNullAndSentimentAnalysisTrue();
        users.forEach(user ->
                System.out.println(user.getName() + " " + user.getEmail()));
        assertNotNull(users);
    }
}
