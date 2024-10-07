package com.app.ebanking.services;

import com.app.ebanking.dtos.*;
import com.app.ebanking.entities.Operation;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {
    CurrentAccountDto saveCurrentAccount(double initialBalance, double overDraft, UUID customerId);
    SavingAccountDto saveSavingAccount(double initialBalance, double interestRate, UUID customerId);
    BankAccountDto getAccount(UUID customerId);
    List<BankAccountDto> getAllAccountsByCustomerId(UUID customerId);
    void debit(UUID accountId, double amount, String description);
    void credit(UUID accountId, double amount, String description);
    void transfer(UUID fromAccountId, UUID toAccountId, double amount);
    BankAccountDto updateBankAccount(BankAccountDto bankAccount);
    void deleteBankAccount(UUID accountId);
    List<OperationDto> getOperationts(UUID accountId);
    AccountHistoryDto accountHistory(UUID accountId,int page,int pageSize);
    List<BankAccountDto> getAllAccounts();
}