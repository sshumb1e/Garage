package com.example.garage.controller;

import com.example.garage.model.Car;
import com.example.garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {

        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }

    @GetMapping("/{id}")
    public String getCar(@PathVariable int id, Model model) {

        model.addAttribute("cars", carService.getCar(id));
        return "car";
    }

    @PutMapping
    public String addCar(@RequestBody Car car) {

         carService.addCar(car);
        return "cars";
    }

    @PostMapping("/{id}")
    public String updateCar(@PathVariable int id, @RequestBody Car car) {

        carService.updateCar(id, car);
        return "cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable int id) {

         carService.deleteCar(id);
        return "cars";
    }





}
