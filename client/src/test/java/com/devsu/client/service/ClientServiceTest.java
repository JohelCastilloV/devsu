package com.devsu.client.service;


import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.model.Client;
import com.devsu.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.Optional;


class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp(){
        clientRepository = Mockito.mock();
        ClientMapper clientMapper = Mockito.mock();
        clientService = new ClientServiceImpl(clientRepository, clientMapper);
    }
    @Test
    void findClientById(){
        String expectedName = "juan";
        Client client = new Client();
        client.setName(expectedName);
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        StepVerifier.create(clientService.retrieve(1L))
                .expectNextMatches(client1 -> expectedName.equals(client1.getName()))
                .verifyComplete();
    }
}
