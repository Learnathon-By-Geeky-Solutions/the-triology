package com.smart.health.care.management.system.dto;

public class LoginUserDto {


    private String phoneNumber;
    private String password;

    // Getters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    // Setters with chaining (fluent style)

    public LoginUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }


}
