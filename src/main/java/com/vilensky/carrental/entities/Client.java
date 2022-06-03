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
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int age;
    private int experience;
    private String phone;
    @Column(name="licencenumber")
    private String licenceNumber;

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private List<RentalOrder> orders = new ArrayList<>();

    public void addRentalOrder(RentalOrder order){
        orders.add(order);
    }
}
