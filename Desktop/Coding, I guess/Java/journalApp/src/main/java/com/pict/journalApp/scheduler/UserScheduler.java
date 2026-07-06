package com.pict.journalApp.scheduler;

import com.pict.journalApp.cache.AppCache;
import com.pict.journalApp.entity.JournalEntry;
import com.pict.journalApp.entity.User;
import com.pict.journalApp.repository.UserRepositoryImpl;
import com.pict.journalApp.service.EmailService;
import com.pict.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService service;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUsersAndSendMail() {
        List<User> users = userRepository.findByEmailIsNotNullAndSentimentAnalysisTrue();
        for(User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries =  journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ",filteredEntries);
            int sentiment = service.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "baccha.syllabus123@gmail.com", "Sentiment for last 7 days", "You were " + sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void clearAppCache() {
        appCache.init();
    }
}
