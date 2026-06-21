package com.pict.service;

import com.pict.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
