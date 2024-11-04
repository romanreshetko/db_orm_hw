package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("countries")
public class Country {
    @Id
    private String countryId;
    private String name;
    private Integer areaSqkm;
    private Integer population;
}
