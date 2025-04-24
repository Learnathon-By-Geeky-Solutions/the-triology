package com.smart.health.care.management.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;  // NOSONAR
import org.springframework.security.core.userdetails.UserDetails;  // NOSONAR

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;  // NOSONAR

@Entity
@Table(name = "patient")
public class Patient implements UserDetails{   // NOSONAR

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)   // NOSONAR
    private String phoneNumber;

    @Column(nullable = false)
    private String password;   // NOSONAR

    @Column(nullable = false)  // NOSONAR
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy") // NOSONAR
    private LocalDate dateOfBirth;

    private int age;

    public Patient() {
    }

    public Patient(int id, String name, String phoneNumber, String password, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;  // NOSONAR
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // NOSONAR
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { // NOSONAR
        return phoneNumber;   // NOSONAR
    }

    @Override
    public boolean isAccountNonExpired() {  // NOSONAR
        return true;  // NOSONAR
    }

    @Override
    public boolean isAccountNonLocked() {  // NOSONAR
        return true;  // NOSONAR
    }

    @Override
    public boolean isCredentialsNonExpired() {  // NOSONAR
        return true;  // NOSONAR
    }

    @Override
    public boolean isEnabled() {  // NOSONAR
        return true;   // NOSONAR
    }

    public int getId() {
        return id;
    }

    public Patient setId(int id) {  // NOSONAR
        this.id = id;
        return this;  // NOSONAR
    }

    public String getName() {
        return name;
    }

    public Patient setName(String name) {  // NOSONAR
        this.name = name;
        return this;  // NOSONAR
    }

    public String getPhoneNumber() {  // NOSONAR
        return phoneNumber;   // NOSONAR
    }

    public Patient setPhoneNumber(String phoneNumber) {// NOSONAR
        this.phoneNumber = phoneNumber;
        return this;  // NOSONAR
    }

    public Patient setPassword(String password) { // NOSONAR
        this.password = password;
        return this;   // NOSONAR
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Patient setDateOfBirth(LocalDate dateOfBirth) {  // NOSONAR
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);  // NOSONAR
        return this;   // NOSONAR
    }

    public int getAge() {
        return age;
    }

    @PrePersist
    @PreUpdate
    public void updateAge() {
        this.age = calculateAge(this.dateOfBirth);
    }

    private int calculateAge(LocalDate dob) {
        return (dob != null) ? Period.between(dob, LocalDate.now()).getYears() : 0;
    }
}