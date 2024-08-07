package com.example.CRUD.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
@Getter
@Setter
@AllArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String Name;

    private double Price;

    private String Nameplate;

    private int Year;


}
