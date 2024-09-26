package com.nycolas.sistemadistribuido.sistemabancario.costumer.service;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerAuthDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.repository.CostumerRepository;
import com.nycolas.sistemadistribuido.sistemabancario.exception.NotFoundedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{
    private final CostumerRepository costumerRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(
            CostumerRepository costumerRepository,
            AuthenticationManager authenticationManager
            ) {
        this.costumerRepository = costumerRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Costumer authenticate(CostumerAuthDTO input) {
        log.trace("Authenticating {}", input);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );
        log.trace("Authenticated.");
        return costumerRepository.findByUsername(input.username())
                .orElseThrow(() -> new NotFoundedException("Costumer not founded."));
    }
}
