package com.example.CRUD.service;

import com.example.CRUD.domain.Car;
import com.example.CRUD.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepo;

    @Transactional
    public List<Car> findAll() {
        return carRepo.findAll();
    }

    @Transactional
    public Optional<Car> findById(Long id) {
        return carRepo.findById(id);
    }

    @Transactional
    public Long insert(Car car) {
        car.setId(null);
        return carRepo.save(car).getId();
    }


    @Transactional
    public Long update(Car newCar) {
        Optional<Car> car = carRepo.findById(newCar.getId());
        car.ifPresent(entity -> {

            entity.setName(newCar.getName());
            entity.setNameplate(newCar.getNameplate());
            entity.setPrice(newCar.getPrice());
            entity.setCarYear(newCar.getCarYear());

            carRepo.save(entity);
        });

        return newCar.getId();
    }

    @Transactional
    public void delete(Long id) {
        carRepo.deleteById(id);
    }


}
