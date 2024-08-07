package com.example.CRUD.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarPutRequestBody {

    @NotNull
    private Long id;

    private String name;

    private double price;

    private String nameplate;

    private int carYear;
}
