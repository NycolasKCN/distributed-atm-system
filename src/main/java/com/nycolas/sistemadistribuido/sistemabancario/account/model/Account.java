package com.nycolas.sistemadistribuido.sistemabancario.account.model;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;
    @Column(name = "type", nullable = false )
    private AccountType type;
    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;
    @Column(name = "current_balance", nullable = false)
    private Double currentBalance;

    public Account() {}

    public Account(Costumer costumer, AccountType type, String accountNumber, Double currentBalance) {
        this.costumer = costumer;
        this.type = type;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", costumer=" + costumer +
                ", type=" + type +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }

    public AccountDTO toDto() {
        return new AccountDTO(costumer.toDto(),type, accountNumber, currentBalance);
    }
}
