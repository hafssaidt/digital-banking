package com.app.ebanking.repositories;

import com.app.ebanking.entities.CurrentAccount;
import com.app.ebanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, UUID> {
}
