package com.smart.health.care.management.system.dto;

public class PatientCreateDto {
    private Long patientId;
    private String gender;
    private String email;
    private String phone;  // This holds the phone number
    private String address;
    private String name;
    private String dateOfBirth;  // Optional field, add if needed

    // Getter and Setter for patientId
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    // Getter and Setter for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for full name (concatenates firstName and lastName)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    // Getter for phone number (returns phone)
    public String getPhoneNumber() {
        return phone;
    }

    // Getter for dateOfBirth (if needed)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // Setter for dateOfBirth
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
