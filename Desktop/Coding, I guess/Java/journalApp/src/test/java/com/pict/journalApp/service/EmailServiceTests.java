package com.pict.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testMail() {
        emailService.sendEmail("shreyaschavan38@gmail.com","baccha.syllabus123@gmail.com","Test Mail","Hi, I am learning SpringBoot");
    }
}
