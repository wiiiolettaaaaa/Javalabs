package com.wioletta.vehicles;

import com.wioletta.passengers.Firefighter;

public class FirefighterCar<T extends Firefighter> extends Car<T> {
    public FirefighterCar(int maxSeats, String name) {
        super(maxSeats, name);
    }
}
