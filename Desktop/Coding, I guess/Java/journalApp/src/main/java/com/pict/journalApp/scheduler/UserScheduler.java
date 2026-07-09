package com.pict.journalApp.scheduler;

import com.pict.journalApp.cache.AppCache;
import com.pict.journalApp.entity.JournalEntry;
import com.pict.journalApp.entity.User;
import com.pict.journalApp.enums.Sentiment;
import com.pict.journalApp.repository.UserRepositoryImpl;
import com.pict.journalApp.service.EmailService;
import com.pict.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            List<Sentiment> sentiments =  journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments) {
                if(sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()) {
                if(entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null) emailService.sendEmail(user.getEmail(), "baccha.syllabus123@gmail.com", "Sentiment for last 7 days", "You were " + mostFrequentSentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void clearAppCache() {
        appCache.init();
    }
}
