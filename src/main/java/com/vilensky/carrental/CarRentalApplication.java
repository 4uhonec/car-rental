package com.vilensky.carrental;

import com.vilensky.carrental.database_fillers.Filler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CarRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);

        //Filler filler = new Filler();
        //filler.fill();
    }
}
