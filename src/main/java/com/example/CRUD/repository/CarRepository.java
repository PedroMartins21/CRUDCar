package com.example.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.CRUD.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
