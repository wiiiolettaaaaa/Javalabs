package com.wioletta;

import com.wioletta.passengers.Firefighter;
import com.wioletta.passengers.Human;
import com.wioletta.passengers.Policeman;
import com.wioletta.road.Road;
import com.wioletta.vehicles.Bus;
import com.wioletta.vehicles.FirefighterCar;
import com.wioletta.vehicles.PoliceCar;
import com.wioletta.vehicles.Taxi;

public class Main {
    public static void main(String[] args) {
        Road road = new Road();
        Bus<Human> bus = new Bus<>(50, "5 DUK");
        Taxi<Human> taxi = new Taxi<>(4, "DriveTrek");
        FirefighterCar<Firefighter> firefighterCar = new FirefighterCar<>(8, "Battalion");
        PoliceCar<Policeman> policeCar = new PoliceCar<>(9, "Duke");

        Human john = new Human("John");
        Human mike = new Human("Mike");
        Firefighter sam = new Firefighter("Firefighter Sam");
        Policeman smith = new Policeman("Officer Smith");

        bus.addPassenger(john);
        taxi.addPassenger(mike);
        firefighterCar.addPassenger(sam);
        policeCar.addPassenger(smith);

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(firefighterCar);
        road.addCarToRoad(policeCar);

        System.out.println("Total passengers on road: " + road.getCountOfHumans());

        road.removeCarFromRoad(policeCar);
        System.out.println("Total passengers on road: " + road.getCountOfHumans());

        bus.removePassenger(john);

        System.out.println("John's current vehicle: " + john.getPassengerVehicle());

    }
}