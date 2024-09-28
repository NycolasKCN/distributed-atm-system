package com.nycolas.sistemadistribuido.sistemabancario.operations.controller;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.operations.dto.TransferRequest;
import com.nycolas.sistemadistribuido.sistemabancario.operations.service.OperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
public class OperationsController {
    private final OperationsService operationsService;

    public OperationsController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deposit")
    public AccountDTO deposit(@RequestParam Double amount,
                              @RequestParam String accountNumber,
                              Authentication authentication) {
        return operationsService.deposit(amount, accountNumber, authentication);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/balance")
    public AccountDTO balance(@RequestParam String accountNumber, Authentication authentication) {
        return operationsService.balance(accountNumber, authentication);

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/withdrawal")
    public AccountDTO withdrawal(@RequestParam String accountNumber,
                                      @RequestParam Double amount,
                                      Authentication authentication) {
       return operationsService.withdrawal(amount, accountNumber, authentication);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transferRequest,
                         Authentication authentication) {
        operationsService.transfer(transferRequest.amount(),
                transferRequest.fromAccountNumber(),
                transferRequest.toAccountNumber(),
                authentication);
    }
}
