package com.temp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "patient")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")// Custom format
    private LocalDate dateOfBirth;

    private int age;

    public Patient() {
    }

    public Patient(int id, String name, String phoneNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);  // Auto-update age when DOB is set
    }

    public int getAge() {
        return age;
    }

    // Auto-calculate age before saving/updating
    @PrePersist
    @PreUpdate
    public void updateAge() {
        this.age = calculateAge(this.dateOfBirth);
    }

    // Method to calculate age
    private int calculateAge(LocalDate dob) {
        return (dob != null) ? Period.between(dob, LocalDate.now()).getYears() : 0;
    }

}
