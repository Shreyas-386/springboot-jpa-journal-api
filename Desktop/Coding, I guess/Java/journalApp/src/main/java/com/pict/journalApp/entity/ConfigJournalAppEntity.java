package com.pict.journalApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigJournalAppEntity {
    @Id
    @Column(name = "config-key")
    private String key;
    @Column(name = "config-value")
    private String value;
}
