package com.app.ebanking.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("SA")
@Entity
public class SavingAccount extends BankAccount{
    private double interestRate;
}
