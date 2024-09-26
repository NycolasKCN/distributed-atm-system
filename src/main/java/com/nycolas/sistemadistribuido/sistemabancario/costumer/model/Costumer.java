package com.nycolas.sistemadistribuido.sistemabancario.costumer.model;

import com.nycolas.sistemadistribuido.sistemabancario.account.model.Account;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CostumerDTO;
import com.nycolas.sistemadistribuido.sistemabancario.costumer.dto.CreateCostumerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="costumers")
public class Costumer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="username", unique=true)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "costumer")
    private List<Account> accounts;

    public Costumer() {

    }

    public Costumer(CreateCostumerDTO costumerDTO) {
        this.username = costumerDTO.username();
        this.email = costumerDTO.email();
        this.firstName = costumerDTO.firstName();
        this.lastName = costumerDTO.lastName();
    }

    public Costumer(CostumerDTO costumerDTO) {
        this.username = costumerDTO.username();
        this.email = costumerDTO.email();
        this.firstName = costumerDTO.firstName();
        this.lastName = costumerDTO.lastName();
    }

    public CostumerDTO toDto() {
        return new CostumerDTO(username, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Costumer costumer = (Costumer) o;
        return username.equals(costumer.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
