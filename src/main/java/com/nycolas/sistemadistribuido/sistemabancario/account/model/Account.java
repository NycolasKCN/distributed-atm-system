package com.nycolas.sistemadistribuido.sistemabancario.account.model;

import com.nycolas.sistemadistribuido.sistemabancario.account.dto.AccountDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.model.Costumer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "accounts")
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

    public Double deposit(double amount) {
        this.currentBalance += amount;
        return this.currentBalance;
    }

    public Double withdrawal(double amount) {
        this.currentBalance -= amount;
        return this.currentBalance;
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

    public AccountDTO toDto() {
        return new AccountDTO(costumer.toDto(), type, accountNumber, currentBalance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(costumer, account.costumer) && type == account.type && Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(costumer);
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(accountNumber);
        return result;
    }


}
