package com.example.CRUD.Mapper;

import com.example.CRUD.domain.Car;
import com.example.CRUD.requests.CarPostRequestBody;
import com.example.CRUD.requests.CarPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public abstract class CarMapper  {

    public abstract Car toCar(CarPostRequestBody carPostRequestBody);

    public abstract Car toCar(CarPutRequestBody carPutRequestBody);
}
