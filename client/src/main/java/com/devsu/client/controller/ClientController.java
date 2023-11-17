package com.devsu.client.controller;

import com.devsu.client.dto.ClientDto;
import com.devsu.client.model.Client;
import com.devsu.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clientes")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping
    Mono<Client> create(@RequestBody Mono<ClientDto> clientDto) {
        return clientService.create(clientDto);
    }

    @GetMapping("/{clientId}")
    Mono<ResponseEntity<Client>> retrieve(@PathVariable Long clientId) {
        return clientService.retrieve(clientId)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{clientId}")
    Mono<ResponseEntity<Client>> update(@PathVariable Long clientId, @RequestBody Mono<ClientDto> clientDto) {
        return clientService.update(clientId, clientDto).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clientId}")
    Mono<Void> delete(@PathVariable Long clientId) {
        return clientService.delete(clientId);
    }

    @GetMapping
    Flux<Client> list() {
        return clientService.list();
    }

}
