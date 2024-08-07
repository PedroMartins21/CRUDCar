package com.example.CRUD.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarPostRequestBody {

    private String name;

    private double price;

    private String nameplate;

    private int carYear;
}
