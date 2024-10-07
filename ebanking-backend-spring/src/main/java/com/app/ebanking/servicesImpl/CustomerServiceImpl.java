package com.app.ebanking.servicesImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.app.ebanking.dtos.CustomerDto;
import com.app.ebanking.entities.Customer;
import com.app.ebanking.repositories.CustomerRepository;
import com.app.ebanking.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ObjectMapper objectMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        Customer customerChecked = customerRepository.findByMail(customer.getMail());
        if (customerChecked != null) {
            throw new RuntimeException("Customer already exists!");
        }
        log.info("Saving new customer");
        Customer customerToSave = objectMapper.convertValue(customer, Customer.class);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return objectMapper.convertValue(savedCustomer, CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomer(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent())
            return objectMapper.convertValue(customer.get(), CustomerDto.class);
        else throw new RuntimeException("customer not found!");
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return objectMapper.convertValue(customers, new TypeReference<List<CustomerDto>>() {});
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) {
        CustomerDto customerChecked = getCustomer(customer.getId());
        customerChecked.setName(customer.getName());
        customerChecked.setMail(customer.getMail());
        Customer customerToUpdate = objectMapper.convertValue(customerChecked, Customer.class);
        Customer customerSaved  = customerRepository.save(customerToUpdate);
        return objectMapper.convertValue(customerSaved, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(UUID id) {
        getCustomer(id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> searchCustomers(String keyword) {
        List<Customer> customers =  customerRepository.findByNameContains(keyword);
        return objectMapper.convertValue(customers, new TypeReference<List<CustomerDto>>() {});
    }
}
