package com.wioletta.vehicles;

import com.wioletta.passengers.Human;
import com.wioletta.road.Road;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final String name;
    private final List<T> passengers;
    private Road road = null;

    public Vehicle(int maxSeats, String name) {
        this.maxSeats = maxSeats;
        this.name = name;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public String getName() {
        return name;
    }

    public void addPassenger(T passenger) {
        if (passengers.size() < maxSeats) {
            if (passenger.getPassengerVehicle() != null)
                throw new IllegalArgumentException("Passenger already has a vehicle");
            passengers.add(passenger);
            passenger.setPassengerVehicle(this);
            System.out.println(passenger.getName() + " was added to the " + name);
        } else {
            throw new IllegalArgumentException("No more seats available");
        }
    }

    public void removePassenger(T passenger) {
        if (passengers.contains(passenger)) {
            passengers.remove(passenger);
            passenger.setPassengerVehicle(null);
            System.out.println(passenger.getName() + " was removed from the " + name);
        } else {
            throw new IllegalArgumentException("Passenger is not in the vehicle");
        }
    }

    public List<T> getPassengers() {
        return passengers;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }
}
