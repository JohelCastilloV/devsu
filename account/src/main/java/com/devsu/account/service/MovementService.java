package com.devsu.account.service;

import com.devsu.account.dto.MovementDto;
import com.devsu.account.model.Movement;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

public interface MovementService {
    @Transactional
    Mono<Movement> create(Mono<MovementDto> movementDtoMono);

    Mono<Movement> retrieve(Long movementId);
}
