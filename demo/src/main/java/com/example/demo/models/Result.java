package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("results")
public class Result {
    @Id
    private Long id;

    private String eventId;

    private String playerId;

    private String medal;

    private Double result;
}
