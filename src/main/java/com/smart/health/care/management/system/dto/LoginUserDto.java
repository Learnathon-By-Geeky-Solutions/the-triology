package com.smart.health.care.management.system.dto;

public class LoginUserDto {


    private String phoneNumber;
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }


}
