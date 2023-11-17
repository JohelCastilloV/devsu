package com.devsu.client.mapper;

import com.devsu.client.dto.ClientDto;
import com.devsu.client.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toClient(ClientDto clientDto);
}
