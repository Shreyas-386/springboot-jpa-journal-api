package com.pict.journalApp.cache;

import com.pict.journalApp.entity.ConfigJournalAppEntity;
import com.pict.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepository repository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = repository.findAll();
        for(ConfigJournalAppEntity entity : all) {
            appCache.put(entity.getKey(), entity.getValue());
        }
    }
}
