package com.example.demo.repository;

import com.example.demo.models.Country;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    @Query("""
            SELECT c.name AS country_name,
                ROUND(
                    100.0 * COUNT(CASE WHEN LOWER(SUBSTRING(p.name, 1, 1)) IN ('a', 'e', 'i', 'o', 'u') THEN 1 END) / COUNT(p.player_id), 2
                ) AS vowel_name_percentage
            FROM countries c
            JOIN players p ON c.country_id = p.country_id
            GROUP BY c.name
            ORDER BY vowel_name_percentage DESC
            LIMIT 1;
            """
    )
    public String findCountryWithBiggestVowelNamePercentage();

    @Query("""
            SELECT c.name, COUNT(r.medal) / c.population AS percentage
            FROM countries c
            JOIN players p ON p.country_id = c.country_id
            JOIN results r ON r.player_id = p.player_id
            JOIN events e ON e.event_id = r.event_id
            JOIN olympics o ON o.olympic_id = e.olympic_id
            WHERE o.year = 2000 AND e.is_team_event = 1
            GROUP BY c.name, c.population
            ORDER BY percentage ASC
            LIMIT 5;
            """
    )
    public List<String> findCountriesWithLowestGroupMedalPercentage();
}
