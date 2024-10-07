package com.app.ebanking.controllers;

import com.app.ebanking.dtos.CustomerDto;
import com.app.ebanking.entities.BankAccount;
import com.app.ebanking.entities.Customer;
import com.app.ebanking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDto customer) {
        try {
            CustomerDto createdCustomer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable UUID customerId) {
        try {
            CustomerDto customer = customerService.getCustomer(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDto> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping( "/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID customerId,@RequestBody CustomerDto customer) {
        try {
            customer.setId(customerId);
            CustomerDto customerUpdated = customerService.updateCustomer(customer);
            return new ResponseEntity<>(customerUpdated, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchCustomers(@RequestParam(defaultValue = "") String keyword) {
        try {
            List<CustomerDto> customers = customerService.searchCustomers(keyword);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
