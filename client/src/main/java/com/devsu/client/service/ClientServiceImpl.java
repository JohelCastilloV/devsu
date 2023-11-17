package com.devsu.client.service;


import com.devsu.client.dto.ClientDto;
import com.devsu.client.mapper.ClientMapper;
import com.devsu.client.model.Client;
import com.devsu.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Mono<Client> create(Mono<ClientDto> clientDto) {
        return clientDto.map(clientMapper::toClient).map(clientRepository::save)
                .publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Client> retrieve(Long clientId) {
        return Mono.fromCallable(() -> this.clientRepository.findById(clientId).orElse(null))
                .publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Client> update(Long clientId, Mono<ClientDto> clientDto) {
        return Mono.fromCallable(() -> this.clientRepository.findById(clientId).orElse(null)).publishOn(Schedulers.boundedElastic())
                .flatMap(user -> clientDto
                        .map(clientMapper::toClient)
                        .doOnNext(u -> u.setId(clientId)))
                .map(clientRepository::save);
    }

    @Override
    public Mono<Void> delete(Long userId) {
        return Mono.fromRunnable(() ->
            this.clientRepository.deleteById(userId)
        ).publishOn(Schedulers.boundedElastic()).then();
    }

    @Override
    public Flux<Client> list() {
        return Mono.fromCallable(this.clientRepository::findAll).flatMapMany(Flux::fromIterable);
    }
}
