package com.temp.dto;

public class LoginDoctorDto {


    private String email;
    private String password;

    // Getters

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters with chaining (fluent style)

    public LoginDoctorDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginDoctorDto setPassword(String password) {
        this.password = password;
        return this;
    }


}
