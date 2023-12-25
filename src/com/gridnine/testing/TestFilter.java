package com.gridnine.testing;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class TestFilter {

    private List<Flight> flights = FlightBuilder.createFlights();

    @Test
    public void getFlightIfMistake() {
        Rule rule1 = new Rule("Удаление ошибок",
                null,
                null,
                true,
                null);
        Filter filter = new Filter(List.of(rule1), flights);

        assertEquals(filter.getResult().size(), 5);
    }

    @Test
    public void deleteFlightBeforeNow() {
        Rule rule1 = new Rule("Удаление перелетов с вылетом до текущего момента",
                LocalDateTime.now(),
                null,
                null,
                null);
        Filter filter = new Filter(List.of(rule1), flights);

        assertEquals(filter.getResult().size(), 5);
    }

    @Test
    public void deleteFlightOnGroundMoreThan() {
        Rule rule1 = new Rule("Удаляем, если время на земле более 2 часов",
                null,
                null,
                null,
                Duration.ofHours(0).plusHours(2));
        Filter filter = new Filter(List.of(rule1), flights);

        assertEquals(filter.getResult().size(), 4);
    }

}
