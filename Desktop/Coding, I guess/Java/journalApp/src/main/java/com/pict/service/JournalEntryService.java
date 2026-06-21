package com.pict.service;

import com.pict.entity.JournalEntry;
import com.pict.entity.User;
import com.pict.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pict.repository.JournalEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService service;

    public List<JournalEntry> getallentries() {
        return journalEntryRepository.findAll();
    }

    public List<JournalEntry> getByUser(User user) {
        return journalEntryRepository.findByEntryOfUsers(user);
    }

    public JournalEntry getbyid(int id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    @Transactional
    public void insertentry(JournalEntry obj) {
        journalEntryRepository.save(obj);
    }

    @Transactional
    public void deleteentry(int id, String username) {
        User user = service.getByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId() == id);
        service.updateUser(user);
        journalEntryRepository.deleteById(id);
    }

    public void updateEntry(int id, JournalEntry obj) {
        JournalEntry journalEntry = journalEntryRepository.findById(id).orElse(null);
        if(journalEntry != null) {
            journalEntry.setContent(obj.getContent());
            journalEntry.setTitle(obj.getTitle());
            journalEntryRepository.save(journalEntry);
        }
    }
}
