package com.wioletta.vehicles;

import com.wioletta.passengers.Human;

class Car<T extends Human> extends Vehicle<T> {
    public Car(int maxSeats, String name) {
        super(maxSeats, name);
    }
}
