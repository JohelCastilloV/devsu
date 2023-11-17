package com.devsu.client.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String name;
    private String genre;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private String password;
    private Boolean status;
}
