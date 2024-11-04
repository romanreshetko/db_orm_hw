package com.example.demo.faker;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private CountryRepository countryRepository;

    private OlympicRepository olympicRepository;

    private PlayerRepository playerRepository;
    private EventRepository eventRepository;
    private ResultRepository resultRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) {
        seedCountries(10);
        seedOlympics(5);
        seedPlayers(20);
        seedEvents(15);
        seedResults(30);
    }

    private void seedCountries(int count) {
        for (int i = 0; i < count; i++) {
            Country country = new Country();
            country.setName(faker.country().name());
            country.setCountryId(faker.address().countryCode());
            country.setAreaSqkm(random.nextInt(1000000) + 10000);
            country.setPopulation(random.nextInt(10000000) + 500000);
            countryRepository.save(country);
        }
    }

    private void seedOlympics(int count) {
        var countriesIterable = countryRepository.findAll();
        List<Country> countries = List.copyOf((Collection<? extends Country>) countriesIterable);
        for (int i = 0; i < count; i++) {
            Olympic olympic = new Olympic();
            olympic.setOlympicId(faker.idNumber().valid());
            olympic.setCountryId(countries.get(random.nextInt(countries.size())).getCountryId());
            olympic.setCity(faker.address().cityName());
            olympic.setYear(2000 + random.nextInt(22));
            olympic.setStartdate(LocalDate.of(2000 + random.nextInt(22), random.nextInt(12) + 1, random.nextInt(28) + 1));
            olympic.setEnddate(olympic.getStartdate().plusDays(random.nextInt(16) + 7));  // от 7 до 23 дней
            olympicRepository.save(olympic);
        }
    }

    private void seedPlayers(int count) {
        var countriesIterable = countryRepository.findAll();
        List<Country> countries = List.copyOf((Collection<? extends Country>) countriesIterable);
        for (int i = 0; i < count; i++) {
            Player player = new Player();
            player.setPlayerId(faker.idNumber().valid());
            player.setName(faker.name().fullName());
            player.setCountryId(countries.get(random.nextInt(countries.size())).getCountryId());
            player.setBirthdate(LocalDate.of(1980 + random.nextInt(25), random.nextInt(12) + 1, random.nextInt(28) + 1));
            playerRepository.save(player);
        }
    }

    private void seedEvents(int count) {
        var olympicsIterable = olympicRepository.findAll();
        List<Olympic> olympics = List.copyOf((Collection<? extends Olympic>) olympicsIterable);
        for (int i = 0; i < count; i++) {
            Event event = new Event();
            event.setEventId(faker.idNumber().valid());
            event.setName(faker.team().name());
            event.setEventtype("Type " + (random.nextInt(5) + 1));
            event.setOlympicId(olympics.get(random.nextInt(olympics.size())).getOlympicId());
            event.setIsTeamEvent(random.nextInt(2));
            event.setNumPlayersInTeam(event.getIsTeamEvent() == 1 ? random.nextInt(10) + 1 : 1);
            event.setResultNotedIn("Time or Distance");
            eventRepository.save(event);
        }
    }

    private void seedResults(int count) {
        var eventsIterable = eventRepository.findAll();
        var playersIterable = playerRepository.findAll();
        List<Event> events = List.copyOf((Collection<? extends Event>) eventsIterable);
        List<Player> players = List.copyOf((Collection<? extends Player>) playersIterable);
        for (int i = 0; i < count; i++) {
            Result result = new Result();
            result.setEventId(events.get(random.nextInt(events.size())).getEventId());
            result.setPlayerId(players.get(random.nextInt(players.size())).getPlayerId());
            result.setMedal(random.nextInt(3) == 0 ? "Gold" : random.nextInt(2) == 0 ? "Silver" : "Bronze");
            result.setResult(random.nextDouble() * 100);
            resultRepository.save(result);
        }
    }
}
