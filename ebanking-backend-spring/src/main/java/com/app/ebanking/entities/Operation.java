package com.app.ebanking.entities;

import com.app.ebanking.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Operation {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;

    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private BankAccount account;
}