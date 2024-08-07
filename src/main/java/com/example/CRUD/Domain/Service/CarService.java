package com.example.CRUD.Domain.Service;

import com.example.CRUD.Data.Repository.CarRepository;
import com.example.CRUD.Domain.Model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepo;

    public List<Car> findAll(){
        return carRepo.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepo.findById(id);
    }

    public Car insert(Car car){
        car.setId(null);
        return carRepo.save(car);
    }


    public Car updateCar( Car newCar) {
        Optional<Car> car = carRepo.findById(newCar.getId());
        car.ifPresent(entity -> {

            entity.setName(newCar.getName());
            entity.setNameplate(newCar.getNameplate());
            entity.setPrice(newCar.getPrice());
            entity.setYear(newCar.getYear());

            carRepo.save(entity);
        });

        return car.orElse(null);
    }

    public void delete(Long id){
        carRepo.deleteById(id);
    }


}
