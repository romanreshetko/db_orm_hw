package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("players")
public class Player {
    @Id
    private String playerId;

    private String name;

    private String countryId;

    private LocalDate birthdate;
}
