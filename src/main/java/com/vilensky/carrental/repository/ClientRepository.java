package com.vilensky.carrental.repository;

import com.vilensky.carrental.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientByLicenceNumber(String licenceNumber);

    @Transactional
    @Modifying
    @Query("delete from Client where licenceNumber = :licenceNumber")
    void deleteByLicenceNumber(String licenceNumber);
}
