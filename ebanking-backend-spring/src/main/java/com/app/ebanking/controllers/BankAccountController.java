package com.app.ebanking.controllers;


import com.app.ebanking.dtos.*;
import com.app.ebanking.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("accounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/current")
    public ResponseEntity<?> saveCurrentAccount(@RequestParam double initialBalance,@RequestParam double overDraft,@RequestParam UUID customerId) {
        try {
            CurrentAccountDto currentAccount = bankAccountService.saveCurrentAccount(initialBalance,overDraft,customerId);
            return new ResponseEntity<>(currentAccount, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/saving")
    public ResponseEntity<?> saveSavingAccount(@RequestParam double initialBalance,@RequestParam double interestRate,@RequestParam UUID customerId) {
        try {
            SavingAccountDto savingAccount = bankAccountService.saveSavingAccount(initialBalance,interestRate,customerId);
            return new ResponseEntity<>(savingAccount, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable UUID accountId) {
        try {
            BankAccountDto bankAccount = bankAccountService.getAccount(accountId);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<BankAccountDto> bankAccounts = bankAccountService.getAllAccounts();
            return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("customer/{customerId}")
    public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable UUID customerId) {
        try {
            List<BankAccountDto> bankAccounts = bankAccountService.getAllAccountsByCustomerId(customerId);
            return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{accountId}/credit")
    public ResponseEntity<?> credit(@PathVariable UUID accountId,@RequestParam double amount,@RequestParam String description) {
        try {
            bankAccountService.credit(accountId,amount,description);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/{accountId}/debit")
    public ResponseEntity<?> debit(@PathVariable UUID accountId,@RequestParam double amount,@RequestParam String description) {
        try {
            bankAccountService.debit(accountId,amount,description);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam UUID fromAccountId,@RequestParam UUID toAccountId,@RequestParam double amount) {
        try {
            bankAccountService.transfer(fromAccountId,toAccountId,amount);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateBankAccount(@PathVariable UUID accountId, @RequestBody BankAccountDto bankAccount, @PathVariable String accountnId) {
        try {
            bankAccount.setId(accountId);
            BankAccountDto bankAccountUpdated = bankAccountService.updateBankAccount(bankAccount);
            return new ResponseEntity<>(bankAccountUpdated, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable UUID accountId) {
        try {
            bankAccountService.deleteBankAccount(accountId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{accountId}/operations")
    public ResponseEntity<?> getHistory(@PathVariable UUID accountId) {
        try {
            List<OperationDto> operations = bankAccountService.getOperationts(accountId);
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{accountId}/accountHistory")
    public ResponseEntity<?> getAccountHistory(@PathVariable UUID accountId,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
        try {
            AccountHistoryDto accountHistory = bankAccountService.accountHistory(accountId,page,size);
            return new ResponseEntity<>(accountHistory, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}