package com.nycolas.sistemadistribuido.sistemabancario.costumer.service;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerAuthDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;

public interface AuthenticationService{
   Costumer authenticate(CostumerAuthDTO input);
}
