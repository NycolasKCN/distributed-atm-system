package com.nycolas.sistemadistribuido.sistemabancario.account.controller;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.dto.CreateAccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path="/v1/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/type/savings")
    public AccountDTO createSavingAccount(@RequestBody CreateAccountDTO createAccountDTO,
                                    @RequestHeader("Authorization") String token) {
        log.trace("Creating account {}", createAccountDTO);
        log.trace("With Token: {}", token.substring(7));
        return this.accountService.createSavingsAccount(createAccountDTO, token.substring(7));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/type/checking")
    public AccountDTO createCheckingAccount(@RequestBody CreateAccountDTO createAccountDTO,
                                            @RequestHeader("Authorization") String token) {
        log.trace("Creating account {}", createAccountDTO);
        log.trace("With Token: {}", token.substring(7));
        return this.accountService.createCheckingAccount(createAccountDTO, token.substring(7));
    }
}
