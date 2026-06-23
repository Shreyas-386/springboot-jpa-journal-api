package com.pict.journalApp.repository;

import com.pict.journalApp.entity.JournalEntry;
import com.pict.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntry,Integer> {
    @Query(value = "SELECT J FROM JournalEntry J WHERE J.user = :user")
    List<JournalEntry> findByEntryOfUsers(@Param("user") User user);
}