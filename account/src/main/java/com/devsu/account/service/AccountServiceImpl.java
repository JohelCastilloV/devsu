package com.devsu.account.service;

import com.devsu.account.dto.AccountDto;
import com.devsu.account.mapper.AccountMapper;
import com.devsu.account.model.Account;
import com.devsu.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public Mono<Account> create(Mono<AccountDto> accountDto) {
        return accountDto.map(accountMapper::toAccount).map(accountRepository::save)
                .publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Account> retrieve(Long accountId) {
        return Mono.fromCallable(() -> this.accountRepository.findById(accountId).orElse(null))
                .publishOn(Schedulers.boundedElastic());
    }
}
