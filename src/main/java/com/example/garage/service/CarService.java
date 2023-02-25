package com.example.garage.service;

import com.example.garage.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class CarService {

    private int idCarsCounter = 0;
    private List<Car> cars = new LinkedList<>();


    public Car addCar(int ownerId, Car car) {

        car.setId(idCarsCounter++);
        car.setUserOwnerId(ownerId);
        cars.add(car);
        return car;
    }

    public Car getCar(int id) {

        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This car not found");
    }

    public List<Car> getAllCars() {

        return cars;
    }

    public Car updateCar(int id, Car car) {

        for (Car car1 : cars) {
            if (car1.getId() == id) {
                car.setBrand(car.getBrand());
                car.setColour(car.getColour());
                return car;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect data type");
    }

    public Car deleteOwnerCar(int ownerId) {

        for (Car car : cars) {
            if (car.getUserOwnerId() == ownerId) cars.removeAll((Collection<?>) car);
            return car;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner's cars not found");
    }

    public Car deleteCar(int id) {

        for (Car car : cars) {
            if (car.getId() == id) cars.remove(car);
            return car;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find car with chosen ID ");
    }

    public Car addCar(Car car) {
        cars.add(idCarsCounter++,car);
        return car;
    }

}
