package com.vilensky.carrental.controllers;

import com.vilensky.carrental.dto.CarDTO;
import com.vilensky.carrental.dto.ClientDTO;
import com.vilensky.carrental.dto.CreateRentalOrderDTO;
import com.vilensky.carrental.dto.RentalOrderDTO;
import com.vilensky.carrental.entities.Client;
import com.vilensky.carrental.entities.RentalOrder;
import com.vilensky.carrental.mappers.CarDTOMapper;
import com.vilensky.carrental.mappers.ClientDTOMapper;
import com.vilensky.carrental.mappers.RentalOrderMapper;
import com.vilensky.carrental.services.CarService;
import com.vilensky.carrental.services.ClientService;
import com.vilensky.carrental.services.RentalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class RentalOrderController {
    private final RentalOrderService rentalOrderService;
    private final RentalOrderMapper rentalOrderMapper;
    private final CarDTOMapper carDTOMapper;
    private final ClientDTOMapper clientDTOMapper;
    private final CarService carService;
    private final ClientService clientService;
    //TODO: delete mock


    @PostMapping
    public ResponseEntity<RentalOrderDTO> saveOrder(@RequestBody CreateRentalOrderDTO createRentalOrderDTO){
        RentalOrder rentalOrder = rentalOrderMapper.mapToOrder(createRentalOrderDTO);
        RentalOrder order = rentalOrderService.saveOrder(rentalOrder);
        return ResponseEntity.status(201).body(rentalOrderMapper.mapToDTO(order));//201 when created
    }

    @GetMapping
    public ResponseEntity<List<RentalOrderDTO>> getAllOrders(@RequestParam String licenceNumber){
        List<RentalOrderDTO> orderDTOList = new ArrayList<>();
        List<RentalOrder> orders = clientService.getOrders(licenceNumber);
        for(RentalOrder o: orders){
            RentalOrderDTO rentalOrderDTO = rentalOrderMapper.mapToDTO(o);
            orderDTOList.add(rentalOrderDTO);
        }
        return ResponseEntity.status(200).body(orderDTOList);
    }

    @GetMapping("/active")
    public ResponseEntity<RentalOrderDTO> getActiveOrder(@RequestParam String licenceNumber){//TODO:checks, maybe change to List<activeOrders>
        Client client = clientService.getClient(licenceNumber);
        RentalOrder activeOrder = clientService.getActiveOrder(licenceNumber);


        return ResponseEntity.status(200).body(RentalOrderDTO.builder()//if active order == null???
                        .rentStart(activeOrder.getRentStart())
                        .rentEnd(activeOrder.getRentEnd())
                        .carDTO(carDTOMapper.mapToDTO(activeOrder.getCar()))
                        .clientDTO(clientDTOMapper.mapToDTO(client))
                        .build());
        //List<RentalOrder> orders = client.getOrders();*/

        /*return ResponseEntity.status(200).body(RentalOrderDTO.builder()
                .rentStart(ZonedDateTime.now())
                        .rentEnd(ZonedDateTime.now().plusDays(3))
                        .carDTO(CarDTO.builder()
                                .carNumber(12345)
                                .color("blue")
                                .price(140)
                                .brand("mazda")
                                .motorVolume(1.2)
                                .model("3")
                                .build())
                        .clientDTO(ClientDTO.builder()
                                .name("Gosha")
                                .phone("423242242")
                                .age(23)
                                .experience(2)
                                .licenceNumber("5463633kkk")
                                .build())
                        .build());*/
    }
    //200- nothing is created
}
