package com.nycolas.sistemadistribuido.sistemabancario.costumer.service;

import com.nycolas.sistemadistribuido.sistemabancario.costumer.repository.CostumerRepository;
import com.nycolas.sistemadistribuido.sistemabancario.exception.NotFoundedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CostumerDetailsServiceImpl implements CostumerDetailsService {

    private final CostumerRepository costumerRepository;

    @Autowired
    public CostumerDetailsServiceImpl(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return costumerRepository.findByUsername(username).orElseThrow(() -> new NotFoundedException("Costumer not found."));
    }

}
