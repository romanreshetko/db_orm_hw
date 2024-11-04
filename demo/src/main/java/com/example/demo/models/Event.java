package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("events")
public class Event {
    @Id
    private String eventId;

    private String name;

    private String eventtype;

    private String olympicId;

    private Integer isTeamEvent;

    private Integer numPlayersInTeam;

    private String resultNotedIn;
}
