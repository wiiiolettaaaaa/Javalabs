package com.wioletta.vehicles;

import com.wioletta.passengers.Policeman;

public class PoliceCar<T extends Policeman> extends Car<T> {
    public PoliceCar(int maxSeats, String name) {
        super(maxSeats, name);
    }
}