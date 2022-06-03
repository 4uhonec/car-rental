package com.vilensky.carrental.services;

import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.exceptions.NoSuchClientException;
import com.vilensky.carrental.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;
    @InjectMocks
    private ClientService clientService;


    Client client = Client.builder()
            .id(UUID.randomUUID())
            .age(20)
            .licenceNumber("gtyruu65")
            .experience(50)
            .name("Kolya")
            .build();

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveClient() {

        when(clientRepositoryMock.save(any())).thenAnswer(execution -> execution.getArguments()[0]);

        assertEquals(client, clientService.saveClient(client));
    }

    @Test
    void getClient(){
        String number = "544";
        when(clientRepositoryMock.findClientByLicenceNumber(number)).thenReturn(Optional.of(client));

        assertEquals(client, clientService.getClient(number));
    }

    @Test
    void getClientException(){
        String number = "544";
        when(clientRepositoryMock.findClientByLicenceNumber(number)).thenReturn(Optional.empty());

        NoSuchClientException noSuchClientException = assertThrows(NoSuchClientException.class, () -> clientService.getClient(number));
        assertEquals("Wrong license number " + number, noSuchClientException.getMessage());
    }


}