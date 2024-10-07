package com.app.ebanking.repositories;

import com.app.ebanking.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, UUID> {
}
