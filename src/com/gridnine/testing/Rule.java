package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;

public class Rule {

    private final String name;

    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    private final Boolean isMistake;

    private final Duration timeOnGround;


    // Динамику можно получить передавая налы вместо аргументов, либо второй вариант можно написать
    // несколько конструкторов
    public Rule(String name,
                LocalDateTime departureDate,
                LocalDateTime arrivalDate,
                Boolean isMistake,
                Duration timeOnGround) {
        this.name = name;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.isMistake = isMistake;
        this.timeOnGround = timeOnGround;
    }

    public Duration getTimeOnGround() {
        return timeOnGround;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getArrivalDate() {
        return departureDate;
    }

    public Boolean getIsMistake() {
        return isMistake;
    }

}
