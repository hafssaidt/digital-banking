package com.app.ebanking.services;

import com.app.ebanking.dtos.CustomerDto;
import com.app.ebanking.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto getCustomer(UUID id);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(CustomerDto customer);
    void deleteCustomer(UUID id);
    List<CustomerDto> searchCustomers(String keyword);
}