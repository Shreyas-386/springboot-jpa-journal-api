package com.pict.journalApp.repository;

import com.pict.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User,Integer> {
    List<User> findByEmailIsNotNullAndSentimentAnalysisTrue();
}
