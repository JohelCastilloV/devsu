package com.devsu.account.service;

import com.devsu.account.dto.ReportDto;
import com.devsu.account.mapper.MovementMapper;
import com.devsu.account.model.Client;
import com.devsu.account.model.Movement;
import com.devsu.account.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final WebClient webClient;

    @Override
    public Flux<ReportDto> retrieveMovementsByClientIdAndDate(LocalDate startDate, LocalDate endDate, Long clientId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}").build(clientId)).retrieve()
                .bodyToMono(Client.class)
                .publishOn(Schedulers.boundedElastic()).map(client->{
                    List<Movement> movements =movementRepository.findByAccount_ClientIdAndCreatedDateBetween(clientId,startDate,endDate);
                    return movements.stream().map(movement -> movementMapper.toReportDto(movement, client)).toList();
                }).flatMapMany(Flux::fromIterable);
    }
}
