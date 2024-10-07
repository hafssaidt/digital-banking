package com.app.ebanking.entities;

import com.app.ebanking.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private String accountType;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account")
    private List<Operation> operations;
}