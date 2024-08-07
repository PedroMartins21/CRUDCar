package com.example.CRUD.requests;

import lombok.Data;

@Data
public class CarPostRequestBody {

    private String name;

    private double price;

    private String nameplate;

    private int carYear;
}
