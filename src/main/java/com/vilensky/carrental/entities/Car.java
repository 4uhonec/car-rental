package com.vilensky.carrental.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="carnumber")
    private int  carNumber;//TODO: make String
    @Column(name="motorvolume")
    private double motorVolume;
    private String color;
    private String model;
    private String brand;
    //private enum category{A,B,C,D,E,F,M} how to implement in dto?
    private double price;

    @OneToMany(mappedBy = "car")
    //@JoinColumn(name = "car_id", referencedColumnName = "id")
    private List<RentalOrder> rentalOrders = new ArrayList<>();
}
