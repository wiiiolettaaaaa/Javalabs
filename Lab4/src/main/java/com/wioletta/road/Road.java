package com.wioletta.road;

import com.wioletta.passengers.Human;
import com.wioletta.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        if (vehicle.getRoad() != null) throw new IllegalArgumentException("Vehicle already has a road");
        carsInRoad.add(vehicle);
        vehicle.setRoad(this);
        System.out.println(vehicle.getName() + " was added to the road");
    }

    public void removeCarFromRoad(Vehicle<? extends Human> vehicle) {
        if (carsInRoad.remove(vehicle)) {
            vehicle.setRoad(null);
            for (Human passenger : vehicle.getPassengers()) {
                passenger.setPassengerVehicle(null);
            }
            System.out.println(vehicle.getName() + " was removed from the road");
        } else {
            throw new IllegalArgumentException("Vehicle does not have a road");
        }
    }

    public int getCountOfHumans() {
        int totalPassengers = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            totalPassengers += vehicle.getOccupiedSeats();
        }
        return totalPassengers;
    }
}
