package com.app.ebanking.servicesImpl;

import com.app.ebanking.dtos.*;
import com.app.ebanking.entities.*;
import com.app.ebanking.enums.AccountStatus;
import com.app.ebanking.enums.OperationType;
import com.app.ebanking.repositories.*;
import com.app.ebanking.services.BankAccountService;
import com.app.ebanking.services.CustomerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CurrentAccountRepository currentAccountRepository;
    private SavingAccountRepository savingAccountRepository;
    private BankAccountRepository bankAccountRepository;
    private CustomerService customerService;
    private OperationRepository operationRepository;
    private ObjectMapper objectMapper;


    @Override
    public CurrentAccountDto saveCurrentAccount(double initialBalance, double overDraft, UUID customerId) {
        CustomerDto customerDto = customerService.getCustomer(customerId);
        Customer customer = objectMapper.convertValue(customerDto, Customer.class);
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCustomer(customer);
        currentAccount.setBalance(initialBalance);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCurrency("DH");
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setAccountType(currentAccount.getClass().getSimpleName());
        CurrentAccount currentAccountSaved = currentAccountRepository.save(currentAccount);
        return objectMapper.convertValue(currentAccountSaved, CurrentAccountDto.class);
    }

    @Override
    public SavingAccountDto saveSavingAccount(double initialBalance, double interestRate, UUID customerId) {
        CustomerDto customerDto = customerService.getCustomer(customerId);
        Customer customer = objectMapper.convertValue(customerDto, Customer.class);
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCustomer(customer);
        savingAccount.setBalance(initialBalance);
        savingAccount.setCurrency("DH");
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(interestRate);
        savingAccount.setAccountType(savingAccount.getClass().getSimpleName());
        SavingAccount savingAccountSaved = savingAccountRepository.save(savingAccount);
        return objectMapper.convertValue(savingAccountSaved, SavingAccountDto.class);
    }

    @Override
    public BankAccountDto getAccount(UUID accountId) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountId);
        if(bankAccount.isEmpty()) {
            throw new RuntimeException("account not found!");
        }

            if (bankAccount.get() instanceof CurrentAccount currentAccount) {
                return objectMapper.convertValue(currentAccount, CurrentAccountDto.class);
            }
            else {
                SavingAccount savingAccount = (SavingAccount) bankAccount.get();
                return objectMapper.convertValue(savingAccount, SavingAccountDto.class);
            }
    }

    @Override
    public List<BankAccountDto> getAllAccountsByCustomerId(UUID customerId) {
        customerService.getCustomer(customerId);
        List<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomerId(customerId);
        return objectMapper.convertValue(bankAccounts, new TypeReference<List<BankAccountDto>>() {});
    }

    @Override
    public void debit(UUID accountId, double amount, String description) {
        Optional<BankAccount> bankAccountChecked = bankAccountRepository.findById(accountId);
        if(bankAccountChecked.isEmpty())
            throw new RuntimeException("account not found!");
        if(bankAccountChecked.get().getBalance() < amount)
                throw new RuntimeException("the balance is not sufficient");
        bankAccountChecked.get().setBalance(bankAccountChecked.get().getBalance() - amount);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccountChecked.get());

    Operation operation = new Operation();
    operation.setType(OperationType.DEBIT);
    operation.setAccount(savedBankAccount);
    operation.setAmount(amount);
    operation.setDescription(description);
    operation.setDate(new Date());
    operationRepository.save(operation);
    }

    @Override
    public void credit(UUID accountId, double amount, String description) {
        Optional<BankAccount> bankAccountChecked = bankAccountRepository.findById(accountId);
        if(bankAccountChecked.isEmpty())
            throw new RuntimeException("account not found!");
        bankAccountChecked.get().setBalance(bankAccountChecked.get().getBalance() + amount);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccountChecked.get());

        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setAccount(savedBankAccount);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setDate(new Date());
        operationRepository.save(operation);
    }

    @Override
    public void transfer(UUID fromAccountId, UUID toAccountId, double amount) {
    debit(fromAccountId, amount, "Transfert to " + toAccountId);
    credit(toAccountId, amount, "Transfert from "+fromAccountId);
    }

    @Override
    public BankAccountDto updateBankAccount(BankAccountDto bankAccount) {
        BankAccountDto bankAccountDto = getAccount(bankAccount.getId());
        bankAccountDto.setBalance(bankAccount.getBalance());
        bankAccountDto.setCurrency(bankAccount.getCurrency());
        bankAccountDto.setStatus(bankAccount.getStatus());
        BankAccount bankAccountToUpdate = objectMapper.convertValue(bankAccountDto, BankAccount.class);
        BankAccount bankAccountSaved = bankAccountRepository.save(bankAccountToUpdate);
        return objectMapper.convertValue(bankAccountSaved, BankAccountDto.class);
    }

    @Override
    public void deleteBankAccount(UUID accountId) {
        getAccount(accountId);
        bankAccountRepository.deleteById(accountId);
    }

    @Override
    public List<OperationDto> getOperationts(UUID accountId) {
        List<Operation> operations = operationRepository.findByAccountId(accountId);
        return objectMapper.convertValue(operations,new TypeReference<List<OperationDto>>() {});
    }

    @Override
    public AccountHistoryDto accountHistory(UUID accountId,int page,int pageSize) {
        BankAccountDto account = getAccount(accountId);
        Page<Operation> operations = (Page<Operation>) operationRepository.findByAccountId(accountId, PageRequest.of(page,pageSize));
        int totalPages = operations.getTotalPages();
        List<OperationDto> operationsDto = objectMapper.convertValue(operations.getContent(),new TypeReference<List<OperationDto>>() {});
        return  new AccountHistoryDto(
        accountId,account.getBalance(),page,totalPages,pageSize,operationsDto);
    }

    @Override
    public List<BankAccountDto> getAllAccounts() {
        List<BankAccount> accounts =  bankAccountRepository.findAll();
        return objectMapper.convertValue(accounts, new TypeReference<List<BankAccountDto>>() {});
    }

}