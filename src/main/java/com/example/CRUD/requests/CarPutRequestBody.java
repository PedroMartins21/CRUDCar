package com.example.CRUD.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarPutRequestBody {

    @NotNull
    private Long id;

    private String name;

    private double price;

    private String nameplate;

    private int carYear;
}
