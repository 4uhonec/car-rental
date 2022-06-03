package com.vilensky.carrental.controllers;

import com.vilensky.carrental.dto.ClientDTO;
import com.vilensky.carrental.dto.RentalOrderDTO;
import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.entities.RentalOrder;
import com.vilensky.carrental.mappers.ClientDTOMapper;
import com.vilensky.carrental.mappers.RentalOrderMapper;
import com.vilensky.carrental.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientDTOMapper clientDTOMapper;
    private final RentalOrderMapper rentalOrderMapper;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO){
        Client client = clientService.saveClient(clientDTOMapper.mapToClient(clientDTO));
        System.out.println("Client created");
        return ResponseEntity.status(201).body(clientDTOMapper.mapToDTO(client));
    }

    @GetMapping("/{licenceNumber}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String licenceNumber){
        Client client = clientService.getClient(licenceNumber);
        return ResponseEntity.ok(clientDTOMapper.mapToDTO(client));
    }

    //@GetMapping("/client/{licenceNumber}/orders")how to return multiple DTOs?


    @GetMapping("/client/{licenceNumber}/activeOrder")//
    public ResponseEntity<RentalOrderDTO> getActiveOrder(@PathVariable String licenceNumber){
        RentalOrder rentalOrder = clientService.getActiveOrder(licenceNumber);
        return ResponseEntity.ok(rentalOrderMapper.mapToDTO(rentalOrder));
    }

    @DeleteMapping("/client/{licenceNumber}")
    public void deleteClient(@PathVariable String licenceNumber){
        clientService.deleteClient(licenceNumber);
        System.out.println("Client with licence number " + licenceNumber + " deleted");
    }
}
