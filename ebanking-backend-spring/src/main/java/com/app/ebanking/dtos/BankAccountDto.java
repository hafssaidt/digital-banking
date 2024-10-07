package com.app.ebanking.dtos;

import com.app.ebanking.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    private UUID id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private String accountType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CustomerDto customer;
    private List<OperationDto> operations;
}
