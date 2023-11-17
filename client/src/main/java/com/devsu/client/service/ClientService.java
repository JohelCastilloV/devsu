package com.devsu.client.service;

import com.devsu.client.dto.ClientDto;
import com.devsu.client.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<Client> create(Mono<ClientDto> clientDto);
    Mono<Client> retrieve(Long clientId);
    Mono<Client> update(Long clientId, Mono<ClientDto> clientDto);
    Mono<Void> delete(Long userId);
    Flux<Client> list();
}
