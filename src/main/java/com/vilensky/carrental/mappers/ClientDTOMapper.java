package com.vilensky.carrental.mappers;

import com.vilensky.carrental.dto.ClientDTO;
import com.vilensky.carrental.entities.Client;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ClientDTOMapper {

    public Client mapToClient(ClientDTO clientDTO){
        return Client.builder()
                .name(clientDTO.getName())
                .licenceNumber(clientDTO.getLicenceNumber())
                .phone(clientDTO.getPhone())
                .age(clientDTO.getAge())
                .experience(clientDTO.getExperience())
                .build();
    }

    public ClientDTO mapToDTO(Client client){
        return ClientDTO.builder()
                .name(client.getName())
                .age(client.getAge())
                .experience(client.getExperience())
                .phone(client.getPhone())
                .licenceNumber(client.getLicenceNumber())
                .build();
    }
}
