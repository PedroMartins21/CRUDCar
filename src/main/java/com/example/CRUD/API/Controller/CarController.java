package com.example.CRUD.API.Controller;

import com.example.CRUD.Domain.Model.Car;
import com.example.CRUD.Domain.Service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.of(Optional.of(carService.findAll()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return ResponseEntity.of(carService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> insert(@RequestBody Car car) {
        return ResponseEntity.of(Optional.of(carService.insert(car)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> update( @RequestBody Car newCar) {
        return ResponseEntity.of(Optional.ofNullable(carService.updateCar(newCar)));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Long id) {
        carService.delete(id);
    }


}
