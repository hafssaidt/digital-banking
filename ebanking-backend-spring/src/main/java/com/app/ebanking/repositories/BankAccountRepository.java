package com.app.ebanking.repositories;

import com.app.ebanking.entities.BankAccount;
import com.app.ebanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    List<BankAccount> findAllByCustomerId(UUID customerId);
}
