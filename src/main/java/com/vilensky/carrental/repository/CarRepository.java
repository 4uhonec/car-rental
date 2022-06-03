package com.vilensky.carrental.repository;

import com.vilensky.carrental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByCarNumber(int carNumber);

    @Transactional
    @Modifying
    @Query("delete from Car where carNumber = :carNumber")
    void deleteByCarNumber(int carNumber);

}
