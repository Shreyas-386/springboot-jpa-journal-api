package com.pict.journalApp.controller;

import com.pict.journalApp.entity.User;
import com.pict.journalApp.service.UserService;
import com.pict.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthcheck() {
        return "OK";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        service.enterUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            System.out.println("1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
            );
            System.out.println("2");
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
            System.out.println("3");
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            System.out.println("4");
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
