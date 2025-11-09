package com.example.javakursinis.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Driver extends BasicUser{
    protected String driverLicence;
    protected LocalDate birthDate;

    public Driver(String login, String password, String name, String surname, String phoneNumber, String address) {
        super(login, password, name, surname, phoneNumber, address);
    }
}
