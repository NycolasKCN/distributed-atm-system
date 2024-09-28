package com.nycolas.sistemadistribuido.sistemabancario.operations.controller;

import com.nycolas.sistemadistribuido.sistemabancario.operations.dto.BalanceResponse;
import com.nycolas.sistemadistribuido.sistemabancario.operations.dto.DepositRequest;
import com.nycolas.sistemadistribuido.sistemabancario.operations.service.OperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/operation")
public class OperationsController {
    private final OperationsService operationsService;

    public OperationsController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositRequest depositRequest, Authentication authentication) {
        operationsService.deposit(depositRequest.amount(), depositRequest.accountNumber(), authentication);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/balance")
    public BalanceResponse balance(@RequestParam String accountNumber, Authentication authentication) {
        return operationsService.balance(accountNumber, authentication);

    }
}
