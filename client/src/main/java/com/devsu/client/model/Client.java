package com.devsu.client.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString(callSuper = true)
public class Client extends Person{
    private String password;
    private Boolean status;
}
