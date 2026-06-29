package com.pict.journalApp.service;

import com.pict.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pict.journalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadByUsernameTest() {
        User user = new User();
        user.setName("Aman");
        user.setPassword("Aman123");

        when(repository.findByName(anyString())).thenReturn(Optional.of(user));

        UserDetails result = userDetailsService.loadUserByUsername("Aman");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Aman", result.getUsername());
    }
}
