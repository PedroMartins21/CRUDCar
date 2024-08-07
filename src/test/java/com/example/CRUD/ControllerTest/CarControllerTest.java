package com.example.CRUD.ControllerTest;


import com.example.CRUD.controller.CarController;
import com.example.CRUD.domain.Car;
import com.example.CRUD.requests.CarPostRequestBody;
import com.example.CRUD.requests.CarPutRequestBody;
import com.example.CRUD.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @InjectMocks
    CarController carController;
    @Mock
    CarService carService;
    Car car;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "Celta", 15000, "xxx1234", 2006);
    }

    @Test
    void getAllCarsTest() {
        List<Car> carList = List.of(car);
        Pageable pageable = Pageable.ofSize(2);
        Page<Car> carPage = new PageImpl<>(carList, pageable, 0);

        when(this.carService.findAll(pageable)).thenReturn(carPage);

        Page<Car> responseEntity = carController.findAll(pageable).getBody();

        assertNotNull(responseEntity);
        assertEquals(carList, responseEntity.getContent());

    }

    @Test
    void getCarByIdTrue() {

        when(this.carService.findById(eq(car.getId()))).thenReturn(Optional.of(car));

        assertEquals(carController.findById(1L).getBody(), car);
    }

    @Test
    void addCarTest() {
        CarPostRequestBody carPostRequestBody = CarPostRequestBody.builder()
                .carYear(2006)
                .name("Celta")
                .price(15000)
                .nameplate("xxx1919")
                .build();
        when(this.carService.insert(carPostRequestBody)).thenReturn(car.getId());

        assertEquals(carController.insert(carPostRequestBody).getBody(), car.getId());

    }

    @Test
    void updateCarTest() {
        CarPutRequestBody carPutRequestBody = CarPutRequestBody.builder()
                .id(1L)
                .carYear(2006)
                .name("Celta")
                .price(15000)
                .nameplate("xxx1919")
                .build();
        when(this.carService.update(carPutRequestBody)).thenReturn(car.getId());
        assertEquals(carController.update(carPutRequestBody).getBody(), car.getId());
    }

    @Test
    void delete() {
        doNothing().when(this.carService).delete(eq(car.getId()));
        carController.delete(car.getId());
        verify(carService).delete(eq(car.getId()));
    }


}
