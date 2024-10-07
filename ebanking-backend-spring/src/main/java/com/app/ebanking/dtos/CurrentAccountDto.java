package com.app.ebanking.dtos;

import com.app.ebanking.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CurrentAccountDto extends BankAccountDto {
    private double overDraft;
}
