package com.example.demo.repository;

import com.example.demo.models.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("""
            SELECT p.name, e.olympic_id
            FROM players p
            JOIN results r ON p.player_id = r.player_id
            JOIN events e ON r.event_id = e.event_id
            WHERE r.medal IN ('Gold', 'Silver', 'Bronze')
            GROUP BY p.name, e.olympic_id
            """
    )
    public List<Object[]> findPlayersWithMedal();
}
