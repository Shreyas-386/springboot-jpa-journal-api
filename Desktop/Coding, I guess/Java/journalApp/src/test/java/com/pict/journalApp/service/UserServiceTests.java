package com.pict.journalApp.service;

import com.pict.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest
public class UserServiceTests {

    @Autowired
    public UserRepository repository;

    @ParameterizedTest
    @ValueSource(strings = {
            "Aman",
            "Amit",
            "Babu",
            "Shyam"
    })
    public void findbyUsername(String name) {
        assertNotNull(repository.findByName(name).orElse(null));
    }
}
