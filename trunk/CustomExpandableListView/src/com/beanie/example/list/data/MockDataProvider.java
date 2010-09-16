
package com.beanie.example.list.data;

import java.util.Random;

import com.beanie.example.list.classes.Bike;
import com.beanie.example.list.classes.Bus;
import com.beanie.example.list.classes.Car;
import com.beanie.example.list.classes.Vehicle;

public class MockDataProvider {
    // A utility method that generates random Vehicles
    public static Vehicle getRandomVehicle(String name) {
        Vehicle vehicle = null;
        Random random = new Random();
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                vehicle = new Car(name);
                break;
            case 1:
                vehicle = new Bus(name);
                break;
            case 2:
                vehicle = new Bike(name);
                break;
        }
        return vehicle;
    }
}
