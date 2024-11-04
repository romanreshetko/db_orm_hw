package com.example.demo.repository;

import com.example.demo.models.Event;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query("""
            SELECT e.event_id, e.name, e.eventtype FROM events e
            JOIN results r ON r.event_id = e.event_id
            WHERE e.is_team_event = 0 AND r.medal = 'Gold'
            GROUP BY e.event_id, e.name, e.eventtype, r.result
            HAVING COUNT(DISTINCT r.player_id) >= 2
            """
    )
    public List<Object[]> findIndividualEventsWithTie();
}
