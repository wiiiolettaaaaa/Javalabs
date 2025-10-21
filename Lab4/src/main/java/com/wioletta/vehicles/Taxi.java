package com.wioletta.vehicles;

import com.wioletta.passengers.Human;

public class Taxi<T extends Human> extends Car<T> {
    public Taxi(int maxSeats, String name) {
        super(maxSeats, name);
    }
}
