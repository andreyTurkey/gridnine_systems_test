package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    private final List<Rule> rules;

    private final List<Flight> flights;

    public Filter(List<Rule> rules, List<Flight> flights) {
        this.rules = rules;
        this.flights = flights;
    }

    public List<Flight> getResult() {
        List<Flight> flightsAfterFilter = null;
        for (Rule rule : rules) {
            System.out.println(rule.getName());
            flightsAfterFilter = flights.stream()
                    .filter(flight -> {
                                if (rule.getIsMistake() != null) {
                                    return getFlightIfMistake(flight);
                                }
                                if (rule.getArrivalDate() != null) {
                                    return deleteFlightBeforeNow(flight);
                                }
                                if (rule.getTimeOnGround() != null) {
                                    return deleteFlightOnGroundMoreThan(flight, rule.getTimeOnGround());
                                }
                                return true;
                            }
                    )
                    .collect(Collectors.toList());
            System.out.println(flightsAfterFilter);
        }
        return flightsAfterFilter;
    }

    private boolean getFlightIfMistake(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean deleteFlightBeforeNow(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                return false;
            }
        }
        return true;
    }

    private boolean deleteFlightOnGroundMoreThan(Flight flight, Duration duration) {
        if (flight.getSegments().size() < 2) {
            return true;
        }

        List<Segment> segments = flight.getSegments();
        Duration timeOnGround;
        LocalDateTime firstArriveTime;
        LocalDateTime nextDepartureTime;

        Duration commonTimeOnGround = Duration.ofHours(0);

        for (int i = 0; i < segments.size(); i++) {
            firstArriveTime = segments.get(i).getArrivalDate();
            if ((i + 1) < segments.size()) {
                nextDepartureTime = segments.get(i + 1).getDepartureDate();
                timeOnGround = Duration.between(firstArriveTime, nextDepartureTime);
                commonTimeOnGround = commonTimeOnGround.plus(timeOnGround);
            }
        }

        return duration.compareTo(commonTimeOnGround) > -1;
    }
}
