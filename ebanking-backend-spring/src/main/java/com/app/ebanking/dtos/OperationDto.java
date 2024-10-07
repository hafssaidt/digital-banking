package com.app.ebanking.dtos;

import com.app.ebanking.entities.BankAccount;
import com.app.ebanking.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OperationDto {
    private UUID id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BankAccountDto account;
}