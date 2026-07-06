package com.pict.journalApp.service;

import com.pict.journalApp.entity.JournalEntry;
import com.pict.journalApp.entity.User;
import com.pict.journalApp.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SentimentAnalysisService {


    public int getSentiment(String text) {
        return 1;
    }
}
