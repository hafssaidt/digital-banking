package com.app.ebanking.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("CA")
@Entity
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
