package com.example.CRUD.service;

import com.example.CRUD.Mapper.CarMapper;
import com.example.CRUD.domain.Car;
import com.example.CRUD.repository.CarRepository;
import com.example.CRUD.requests.CarPostRequestBody;
import com.example.CRUD.requests.CarPutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepo;
    private final CarMapper carMapper;

    @Transactional
    public Page<Car> findAll(Pageable pageable) {
        return carRepo.findAll(pageable);
    }

    @Transactional
    public Optional<Car> findById(Long id) {
        return carRepo.findById(id);
    }

    @Transactional
    public Long insert(CarPostRequestBody car) {
        return carRepo.save(carMapper.toCar(car)).getId();
    }

    @Transactional
    public Long update(CarPutRequestBody carPutRequestBody) {
        return carRepo.save(carMapper.toCar(carPutRequestBody)).getId();
    }

    @Transactional
    public void delete(Long id) {
        carRepo.deleteById(id);
    }

}
