package com.vilensky.carrental.services;

import com.vilensky.carrental.dto.RentalOrderDTO;
import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.entities.RentalOrder;
import com.vilensky.carrental.repository.RentalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RentalOrderService {
    private final RentalOrderRepository rentalOrderRepository;

    //private final CarService carService;
    private final ClientService clientService;

    public RentalOrder saveOrder(RentalOrder rentalOrder){
        return rentalOrderRepository.save(rentalOrder);
    }

    public List<RentalOrder> getAllOrdersForCarNumber(int carNumber){
        return clientService.getAllClients().stream()
                .map(Client::getOrders)
                .flatMap(Collection::stream)
                .filter(rentalOrder -> rentalOrder.getCar().getCarNumber() == carNumber).collect(Collectors.toList());
    }

    //if client does not exist
}
