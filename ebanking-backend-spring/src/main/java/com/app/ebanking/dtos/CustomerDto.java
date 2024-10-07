package com.app.ebanking.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerDto {
    private UUID id;
    private String name;
    private String mail;
}
