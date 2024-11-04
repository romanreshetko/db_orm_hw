package com.example.demo.repository;

import com.example.demo.models.Olympic;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface OlympicRepository extends CrudRepository<Olympic, Integer> {

    @Query("""
            SELECT
                        EXTRACT(YEAR FROM p.birthdate) AS birthYear,
                        COUNT(DISTINCT p.player_id) AS playerCount,
                        COUNT(r.medal) AS goldMedalCount
                    FROM olympics o
                    JOIN events e ON o.olympic_id = e.olympic_id
                    JOIN results r ON e.event_id = r.event_id
                    JOIN players p ON r.player_id = p.player_id
                    WHERE o.year = 2004 AND r.medal = 'Gold'
                    GROUP BY birthYear
                    HAVING COUNT(r.medal) > 0
            """
    )
    public List<Object[]> findGoldMedalStatsFor2004Olympics();
}
