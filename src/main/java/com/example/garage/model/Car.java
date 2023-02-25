package com.example.garage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {

    private int id;
    private String brand;
    private String colour;
    private int userOwnerId;

}
