package com.vilensky.carrental.mappers;

import com.vilensky.carrental.dto.CreateRentalOrderDTO;
import com.vilensky.carrental.dto.RentalOrderDTO;
import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.entities.RentalOrder;
import com.vilensky.carrental.services.CarService;
import com.vilensky.carrental.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalOrderMapper {
    private final ClientDTOMapper clientDTOMapper;
    private final CarDTOMapper carDTOMapper;
    private final CarService carService;
    private final ClientService clientService;

    public RentalOrder mapToOrder(CreateRentalOrderDTO createRentalOrderDTO){
        Car car = carService.getCar(createRentalOrderDTO.getCarNumber());
        Client client = clientService.getClient(createRentalOrderDTO.getLicenceNumber());
        return RentalOrder.builder()
                .rentStart(createRentalOrderDTO.getRentStart())
                .rentEnd(createRentalOrderDTO.getRentEnd())
                .car(car)
                .client(client)
                .build();
    }

    public RentalOrderDTO mapToDTO(RentalOrder rentalOrder){
        return RentalOrderDTO.builder()
                .rentStart(rentalOrder.getRentStart())
                .rentEnd(rentalOrder.getRentEnd())
                .clientDTO(clientDTOMapper.mapToDTO(rentalOrder.getClient()))
                .carDTO(carDTOMapper.mapToDTO(rentalOrder.getCar()))
                .build();
    }
}
