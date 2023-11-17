package com.devsu.account.controller;

import com.devsu.account.dto.MovementDto;
import com.devsu.account.model.Movement;
import com.devsu.account.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/movimientos")
@RequiredArgsConstructor
public class MovementController {
    private final MovementService movementService;

    @PostMapping
    Mono<Movement> create(@RequestBody Mono<MovementDto> movementDtoMono) {
        return movementService.create(movementDtoMono);
    }
    @GetMapping("/{movementId}")
    Mono<ResponseEntity<Movement>> retrieve(@PathVariable Long movementId) {
        return movementService.retrieve(movementId)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
