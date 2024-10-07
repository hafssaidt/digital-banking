package com.app.ebanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    private String mail;
    @OneToMany(mappedBy = "customer")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private List<BankAccount> accounts;
}
