package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.model.User;
import com.example.garage.service.CarService;
import com.example.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private CarService carService;

    @Autowired
    public UserController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id, Model model) {

        model.addAttribute("owners", userService.getUser(id));
        return "user";
    }

    @GetMapping
    public String getAllUsers(Model model) {

        model.addAttribute("owners", userService.getAllUsersAndCars());
        return "users";
    }

    @PutMapping
    public String addUser(@RequestBody User user) {

         userService.addUser(user);
        return "users";
    }


    @PostMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {

         userService.updateUser(id, user);
        return "users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return "users";
    }

    @PostMapping("/{id}/addcar")
    public String addUserCar(@PathVariable int id, @RequestBody Car car) {

         userService.addUserCar(id,car);
        return "users";
    }

    @DeleteMapping("/deletecar/{ownerId}/{carId}")
    public String deleteUserCar(@PathVariable int ownerId, @PathVariable int carId) {
         userService.deleteUserCar(ownerId, carId);
        return "users";
    }





}
