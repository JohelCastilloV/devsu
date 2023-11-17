package com.devsu.account.service;

import com.devsu.account.dto.AccountDto;
import com.devsu.account.model.Account;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> create(Mono<AccountDto> accountDto);

    Mono<Account> retrieve(Long accountId);
}
