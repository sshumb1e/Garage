package com.example.garage.service;

import com.example.garage.model.Car;
import com.example.garage.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private CarService carService;
    private final HashMap<User, List<Car>> owners = new HashMap<>();
    private int idUsersCounter = 1;



    public UserService(CarService carService) {
        this.carService = carService;
    }

    public User addUser(User user) {

        user.setId(idUsersCounter++);
        owners.put(user,new LinkedList<>());
        return user;
    }

    public Map<User, List<Car>> getAllUsersAndCars() {

        return owners;
    }

    public User getUser(int id) {

        for (User user : owners.keySet()) {
            if (user.getId() == id) return user;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this ID now found");
    }

    public User updateUser(int id,User owner) {

        for (User user : owners.keySet()) {
            if (user.getId() == id) {
                user.setName(owner.getName());
                user.setAge(owner.getAge());
                return user;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect data input");
    }

    public User deleteUser(int id) {

        for (User user : owners.keySet()) {
            if (user.getId() == id) owners.remove(user);
            carService.deleteOwnerCar(id);
            return user;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public Car addUserCar(int id, Car car) {

        Car vehicle = carService.addCar(id,car);
        owners.get(getUser(id)).add(car);
        return car;
    }

    public Car deleteUserCar(int ownerID, int carID) {

        Car vehicle = carService.getCar(carID);
        owners.remove(getUser(ownerID)).remove(vehicle);
        carService.deleteCar(carID);
        return vehicle;

    }


}
