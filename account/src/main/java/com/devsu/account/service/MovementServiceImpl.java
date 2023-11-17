package com.devsu.account.service;

import com.devsu.account.dto.MovementDto;
import com.devsu.account.exception.AccountNotFoundException;
import com.devsu.account.exception.InsufficientBalanceException;
import com.devsu.account.mapper.MovementMapper;
import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.repository.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService{
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;

    @Override
    @Transactional
    public Mono<Movement> create(Mono<MovementDto> movementDtoMono) {
        return movementDtoMono.publishOn(Schedulers.boundedElastic()).map(movementDto -> {
            Account account = accountRepository.findById(movementDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
            BigDecimal finalBalance = account.getBalance().add(movementDto.getAmount());
            if(account.getBalance().add(movementDto.getAmount()).compareTo(BigDecimal.ZERO)<0){
                throw new InsufficientBalanceException("Saldo no disponible");
            }
            Movement movement = movementMapper.toMovement(movementDto);
            movement.setPreviousBalance(account.getBalance());
            movementRepository.save(movement);
            account.setBalance(finalBalance);
            accountRepository.save(account);
            return movement;
        });

    }

    @Override
    public Mono<Movement> retrieve(Long movementId) {
        return Mono.fromCallable(() -> this.movementRepository.findById(movementId).orElse(null))
                .publishOn(Schedulers.boundedElastic());
    }
}
