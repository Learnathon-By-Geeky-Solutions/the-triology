package com.smart.health.care.management.system.dto;

public class LoginDoctorDto {


    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginDoctorDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginDoctorDto setPassword(String password) {
        this.password = password;
        return this;
    }


}
