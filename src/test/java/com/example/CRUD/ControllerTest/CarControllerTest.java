package com.example.CRUD.ControllerTest;

import com.example.CRUD.API.Controller.CarController;
import com.example.CRUD.Domain.Model.Car;
import com.example.CRUD.Domain.Service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        when(this.carService.findAll()).thenReturn(carList);

        ResponseEntity<List<Car>> responseEntity = carController.findAll();

        assertEquals(carList, responseEntity.getBody());

    }

    @Test
    void getCarByIdTrue() {

        when(this.carService.findById(eq(car.getId()))).thenReturn(Optional.of(car));

        assertEquals(carController.findById(1L).getBody(), car);
    }

    @Test
    void addCarTest() {

        when(this.carService.insert(car)).thenReturn(car);

        assertEquals(carController.insert(car).getBody(), car);

    }

    @Test
    void updateCarTest() {
        when(this.carService.update(car)).thenReturn(car);
        assertEquals(carController.update(car).getBody(), car);
    }

    @Test
    void delete() {
        doNothing().when(this.carService).delete(eq(car.getId()));
        carController.delete(car.getId());
        verify(carService).delete(eq(car.getId()));
    }


}
