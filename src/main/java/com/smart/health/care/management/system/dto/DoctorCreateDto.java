package com.smart.health.care.management.system.dto;

public class DoctorCreateDto {
    private String doctorName;

    private String doctorSpeciality;
    private String doctorExperience;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorPassword;

    // Getters and Setters with Method Chaining
    public String getDoctorName() {
        return doctorName;
    }

    public DoctorCreateDto setDoctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public DoctorCreateDto setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
        return this;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public DoctorCreateDto setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
        return this;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public DoctorCreateDto setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
        return this;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public DoctorCreateDto setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
        return this;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public DoctorCreateDto setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
        return this;
    }

}
