package com.vilensky.carrental.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="rentalorder")
public class RentalOrder {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="rentstart")
    private ZonedDateTime rentStart;
    @Column(name="rentend")
    private ZonedDateTime rentEnd;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
