package com.wioletta;

import com.wioletta.passengers.Firefighter;
import com.wioletta.passengers.Human;
import com.wioletta.passengers.Policeman;
import com.wioletta.vehicles.Bus;
import com.wioletta.vehicles.FirefighterCar;
import com.wioletta.vehicles.PoliceCar;
import com.wioletta.vehicles.Taxi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    private Bus<Human> bus;
    private Taxi<Human> taxi;
    private FirefighterCar<Firefighter> firefighterCar;
    private PoliceCar<Policeman> policeCar;
    private Human john;
    private Human mike;
    private Firefighter sam;
    private Policeman smith;

    @Before
    public void setUp() {
        bus = new Bus<>(50, "City Bus");
        taxi = new Taxi<>(4, "TaxiCab");
        firefighterCar = new FirefighterCar<>(8, "Firefighter Truck");
        policeCar = new PoliceCar<>(9, "Police Cruiser");

        john = new Human("John");
        mike = new Human("Mike");
        sam = new Firefighter("Sam");
        smith = new Policeman("Smith");
    }

    @Test
    public void testAddPassengerThrowsExceptionIfPassengerAlreadyHasVehicle() {
        bus.addPassenger(john);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bus.addPassenger(john));
        assertEquals("Passenger already has a vehicle", exception.getMessage());
    }

    @Test
    public void testRemovePassengerThrowsExceptionIfPassengerIsNotInTheVehicle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bus.removePassenger(john));
        assertEquals("Passenger is not in the vehicle", exception.getMessage());
    }

    @Test
    public void testGetMaxSeats() {
        assertEquals(50, bus.getMaxSeats());
        assertEquals(4, taxi.getMaxSeats());
    }

    @Test
    public void testAddHumanPassengersToBus() {
        bus.addPassenger(john);
        bus.addPassenger(mike);

        assertEquals(2, bus.getOccupiedSeats(), "Bus should have 2 passengers.");
        assertTrue(bus.getPassengers().contains(john), "John should be in the bus.");
        assertTrue(bus.getPassengers().contains(mike), "Mike should be in the bus.");
    }

    @Test
    public void testAddFirefighterToFirefighterCar() {
        firefighterCar.addPassenger(sam);

        assertEquals(1, firefighterCar.getOccupiedSeats(), "Firefighter car should have 1 firefighter.");
        assertTrue(firefighterCar.getPassengers().contains(sam), "Sam should be in the firefighter car.");
        assertEquals(firefighterCar, sam.getPassengerVehicle(), "Sam's vehicle should be the firefighter car.");
    }

    @Test
    public void testAddPolicemanToPoliceCar() {
        policeCar.addPassenger(smith);

        assertEquals(1, policeCar.getOccupiedSeats(), "Police car should have 1 policeman.");
        assertTrue(policeCar.getPassengers().contains(smith), "Smith should be in the police car.");
        assertEquals(policeCar, smith.getPassengerVehicle(), "Smith's vehicle should be the police car.");
    }

    @Test
    public void testAddMixedPassengersToTaxi() {
        taxi.addPassenger(john);
        taxi.addPassenger(sam);
        taxi.addPassenger(smith);

        assertEquals(3, taxi.getOccupiedSeats(), "Taxi should have 3 passengers.");
        assertTrue(taxi.getPassengers().contains(john), "John should be in the taxi.");
        assertTrue(taxi.getPassengers().contains(sam), "Sam should be in the taxi.");
        assertTrue(taxi.getPassengers().contains(smith), "Smith should be in the taxi.");
    }

    @Test
    public void testRemovePassengerFromVehicle() {
        bus.addPassenger(john);
        bus.removePassenger(john);

        assertEquals(0, bus.getOccupiedSeats(), "Bus should have no passengers after removing John.");
        assertNull(john.getPassengerVehicle(), "John's vehicle should be null after removal.");
    }

    @Test
    public void testNoMoreSeatsInTaxi() {
        taxi.addPassenger(john);
        taxi.addPassenger(mike);
        taxi.addPassenger(sam);
        taxi.addPassenger(smith);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> taxi.addPassenger(new Human("Another Passenger")));
        assertEquals("No more seats available", exception.getMessage(), "Expected exception when adding a passenger to a full taxi.");
    }
}