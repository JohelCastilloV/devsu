package com.devsu.account.controller;

import com.devsu.account.dto.AccountDto;
import com.devsu.account.model.Account;
import com.devsu.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/cuentas")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    Mono<Account> create(@RequestBody Mono<AccountDto> accountDtoMono) {
        return accountService.create(accountDtoMono);
    }

    @GetMapping("/{accountId}")
    Mono<ResponseEntity<Account>> retrieve(@PathVariable Long accountId) {
        return accountService.retrieve(accountId)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
