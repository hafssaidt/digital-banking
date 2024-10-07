package com.app.ebanking.repositories;

import com.app.ebanking.dtos.CustomerDto;
import com.app.ebanking.entities.BankAccount;
import com.app.ebanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByMail(String mail);

    List<Customer> findByNameContains(String keyword);
}
