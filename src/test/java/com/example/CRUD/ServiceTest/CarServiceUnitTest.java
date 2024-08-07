package com.example.CRUD.ServiceTest;


import com.example.CRUD.Mapper.CarMapper;
import com.example.CRUD.domain.Car;
import com.example.CRUD.repository.CarRepository;
import com.example.CRUD.requests.CarPostRequestBody;
import com.example.CRUD.requests.CarPutRequestBody;
import com.example.CRUD.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceUnitTest {
    @InjectMocks
    CarService carService;
    @Mock
    CarRepository carRepository;
    @Mock
    CarMapper carMapper;
    Car car;

    @BeforeEach
    void setUp() {
        car = new Car(1L, "Celta", 15000, "xxx1234", 2006);
    }

    @Test
    void getAllCarsTestTrue() {
        Pageable pageable = Pageable.ofSize(2);
        List<Car> carList = List.of(car);
        Page<Car> carPage = new PageImpl<>(carList, pageable, 0);
        when(this.carRepository.findAll(pageable)).thenReturn(carPage);

        Page<Car> cars = carService.findAll(pageable);

        assertEquals(carList, cars.getContent());

    }

    @Test
    void getCarByIdTrue() {

        when(this.carRepository.findById(eq(car.getId()))).thenReturn(Optional.of(car));

        assertEquals(carService.findById(1L), Optional.of(car));
    }

    @Test
    void addCarTestTrue() {
        ArgumentCaptor<CarPostRequestBody> carCaptor = ArgumentCaptor.forClass(CarPostRequestBody.class);
        when(this.carRepository.save(any())).thenReturn(car);
        when(carMapper.toCar(any(CarPostRequestBody.class))).thenReturn(car);
        carService.insert(CarPostRequestBody.builder().name("Celta").build());
        verify(carMapper).toCar(carCaptor.capture());
        assertEquals(carCaptor.getValue().getName(), "Celta");
    }

    @Test
    void updateCarTestTrue() {
        ArgumentCaptor<CarPutRequestBody> carCaptor = ArgumentCaptor.forClass(CarPutRequestBody.class);
        when(this.carRepository.save(any())).thenReturn(car);
        when(carMapper.toCar(any(CarPutRequestBody.class))).thenReturn(car);
        carService.update(CarPutRequestBody.builder().id(1L).build());
        verify(carMapper).toCar(carCaptor.capture());
        assertEquals(carCaptor.getValue().getId(), 1L);
    }

    @Test
    void deleteTrue() {
        doNothing().when(this.carRepository).deleteById(eq(car.getId()));
        carService.delete(car.getId());
        verify(carRepository).deleteById(eq(car.getId()));
    }



}
