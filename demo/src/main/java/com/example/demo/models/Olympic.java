package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("olympics")
public class Olympic {
    @Id
    private String olympicId;

    private String countryId;

    private String city;

    private Integer year;

    private LocalDate startdate;

    private LocalDate enddate;
}
