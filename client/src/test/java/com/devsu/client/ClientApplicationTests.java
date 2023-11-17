package com.devsu.client;

import com.devsu.client.controller.ClientController;
import com.devsu.client.dto.ClientDto;
import com.devsu.client.mapper.ClientMapperImpl;
import com.devsu.client.model.Client;
import com.devsu.client.repository.ClientRepository;
import com.devsu.client.service.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ClientController.class)
@Import({ClientServiceImpl.class, ClientMapperImpl.class})
class ClientApplicationTests {

    @MockBean
    ClientRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateEmployee() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Test");
        Client client = new Client();
        Mockito.when(repository.save(any())).thenReturn(client);
        webClient.post()
                .uri("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(clientDto), ClientDto.class)
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(repository, times(1)).save(any());
    }

}
