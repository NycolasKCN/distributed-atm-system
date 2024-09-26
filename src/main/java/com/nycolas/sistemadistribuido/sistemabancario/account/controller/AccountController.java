package com.nycolas.sistemadistribuido.sistemabancario.account.controller;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.account.service.AccountService;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public AccountDTO createSavingAccount(Authentication auth) {
        Costumer logedCostumer = (Costumer) auth.getPrincipal();
        log.trace("Creating account with costumer {}", logedCostumer);
        return this.accountService.createSavingsAccount(logedCostumer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/type/checking")
    public AccountDTO createCheckingAccount(Authentication auth) {
        Costumer logedCostumer = (Costumer) auth.getPrincipal();
        log.trace("Creating account with costumer {}", logedCostumer);
        return this.accountService.createCheckingAccount(logedCostumer);
    }


}
