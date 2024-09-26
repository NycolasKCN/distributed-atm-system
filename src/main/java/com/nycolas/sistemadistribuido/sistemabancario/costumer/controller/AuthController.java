package com.nycolas.sistemadistribuido.sistemabancario.costumer.controller;

import com.nycolas.sistemadistribuido.sistemabancario.config.JwtService;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerAuthDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.JwtTokenDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("v1/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(AuthenticationService authService, JwtService jwtService) {
        this.authenticationService = authService;
        this.jwtService = jwtService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public JwtTokenDTO authenticateUser(@RequestBody CostumerAuthDTO costumerAuthDTO) {
       log.trace("Trying to authenticate costumer: {}",  costumerAuthDTO.toString());
       Costumer authenticatedCostumer = authenticationService.authenticate(costumerAuthDTO);
       log.trace("Authenticated costumer: {}", authenticatedCostumer.toString());
       String token = jwtService.buildToken(authenticatedCostumer);

       return new JwtTokenDTO(token);
    }
}
