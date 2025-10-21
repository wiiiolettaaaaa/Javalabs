package com.wioletta.vehicles;

import com.wioletta.passengers.Human;

public class Bus<T extends Human> extends Vehicle<T> {
    public Bus(int maxSeats, String name) {
        super(maxSeats, name);
    }
}