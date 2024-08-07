package com.example.CRUD.controller;

import com.example.CRUD.domain.Car;
import com.example.CRUD.requests.CarPostRequestBody;
import com.example.CRUD.requests.CarPutRequestBody;
import com.example.CRUD.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Car>> findAll(Pageable pageable) {
        return ResponseEntity.of(Optional.of(carService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return ResponseEntity.of(carService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> insert(@RequestBody CarPostRequestBody car) {
        return ResponseEntity.of(Optional.of(carService.insert(car)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> update(@Valid @RequestBody CarPutRequestBody newCar) {
        return ResponseEntity.of(Optional.ofNullable(carService.update(newCar)));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Long id) {
        carService.delete(id);
    }


}
