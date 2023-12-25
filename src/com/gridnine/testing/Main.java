package com.gridnine.testing;

import com.gridnine.testing.Filter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Rule> rules = List.of(new Rule("Удаление ошибок",
                        null,
                        null,
                        true,
                        null),
                new Rule("Удаление перелетов с вылетом до текущего момента",
                        LocalDateTime.now(),
                        null,
                        null,
                        null),
                new Rule("Удаляем, если время на земле более 2 часов",
                        null,
                        null,
                        null,
                        Duration.ofHours(0).plusHours(2)));

        Filter filter = new Filter(rules, FlightBuilder.createFlights());
        filter.getResult();
    }
}