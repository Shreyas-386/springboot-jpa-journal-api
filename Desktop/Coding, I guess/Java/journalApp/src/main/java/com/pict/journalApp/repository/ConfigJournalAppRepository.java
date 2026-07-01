package com.pict.journalApp.repository;

import com.pict.journalApp.entity.ConfigJournalAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigJournalAppRepository extends JpaRepository<ConfigJournalAppEntity, String> {
}
