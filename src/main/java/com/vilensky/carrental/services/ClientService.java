package com.vilensky.carrental.services;

import com.vilensky.carrental.dto.ClientDTO;
import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.entities.RentalOrder;
import com.vilensky.carrental.exceptions.NoSuchClientException;
import com.vilensky.carrental.repository.ClientRepository;
import com.vilensky.carrental.repository.RentalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final CarService carService;
    private final ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClient(String licenceNumber){
        Optional<Client> byLicenceNumber = clientRepository.findClientByLicenceNumber(licenceNumber);
        if(byLicenceNumber.isPresent()) return byLicenceNumber.get();
        throw new NoSuchClientException("Wrong license number " + licenceNumber);
    }
    //??
    @Transactional
    public Client addOrder(String licenceNumber, RentalOrder order){
        Client client = getClient(licenceNumber);
        client.addRentalOrder(order);
        return clientRepository.save(client);//we need to add order to specific car, how? do we really need to do it?
    }
    //??
    public List<RentalOrder> getOrders(String licenceNumber){
        Optional<Client> byLicenceNumber = clientRepository.findClientByLicenceNumber(licenceNumber);
        if(byLicenceNumber.isPresent()) return byLicenceNumber.get().getOrders();
        throw new NoSuchClientException("No such client");
    }
    //??
    public RentalOrder getActiveOrder(String licenceNumber){//change to many?
        List<RentalOrder> orders = getOrders((licenceNumber));
        List<RentalOrder> collect = orders.stream()
                .filter(rentalOrder -> rentalOrder.getRentEnd().isAfter(ZonedDateTime.now()) && rentalOrder.getRentStart().isBefore(ZonedDateTime.now()))
                .collect(Collectors.toList());
        if(collect.size() == 1) return collect.get(0);//TODO: 3 tests
        if(collect.size() > 1) throw new RuntimeException("Error, umber of active orders more than 1");
        return null;
    }

    @Transactional
    public void deleteClient(String licenceNumber){
        clientRepository.deleteByLicenceNumber(licenceNumber);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
}
