package com.app.ebanking.dtos;

import com.app.ebanking.entities.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryDto {
    private UUID accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<OperationDto> operations;
}
