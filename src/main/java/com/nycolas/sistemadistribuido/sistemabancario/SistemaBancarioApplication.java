package com.nycolas.sistemadistribuido.sistemabancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SistemaBancarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaBancarioApplication.class);
    }

    @GetMapping("/replica")
    public String getReplicaIndex() {
        String replicaIndex = System.getenv("REPLICA_INDEX");
        return "Replica Index: " + (replicaIndex != null ? replicaIndex : "Not replicated.");
    }
}
