package com.wioletta.passengers;

import com.wioletta.vehicles.Vehicle;

public class Human {
    private final String name;
    private Vehicle<? extends Human> passengerVehicle = null;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vehicle<? extends Human> getPassengerVehicle() {
        return passengerVehicle;
    }

    public void setPassengerVehicle(Vehicle<? extends Human> passengerVehicle) {
        this.passengerVehicle = passengerVehicle;
    }
}
