package com.smart.health.care.management.system.model;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;  // NOSONAR
import org.springframework.security.core.userdetails.UserDetails;  // NOSONAR

import java.util.Collection;  // NOSONAR
import java.util.List;  // NOSONAR

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Doctor")

public class Doctor implements UserDetails {  // NOSONAR

    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String experience;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)  // NOSONAR
    private String password;   // NOSONAR

    public Doctor()
    {

    }
    // Parameterized Constructor
    public Doctor(String name, String specialty, String experience, String email, String phone, String password) {


        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
        this.email = email;
        this.phone = phone;
        this.password = password;  // NOSONAR
    }

    @Override // NOSONAR
    public Collection<? extends GrantedAuthority> getAuthorities() { // NOSONAR
        return List.of();  // NOSONAR
    }

    @Override  // NOSONAR
    public String getPassword() {  // NOSONAR
        return password;   // NOSONAR
    }

    @Override  // NOSONAR
    public String getUsername() {  // NOSONAR
        return email;  // NOSONAR
    }

    @Override  // NOSONAR
    public boolean isAccountNonExpired() {  // NOSONAR
        return true;  // NOSONAR
    }

    @Override // NOSONAR
    public boolean isAccountNonLocked() { // NOSONAR
        return true; // NOSONAR
    }

    @Override // NOSONAR
    public boolean isCredentialsNonExpired() {  // NOSONAR
        return true; // NOSONAR
    }

    @Override // NOSONAR
    public boolean isEnabled() { // NOSONAR
        return true; // NOSONAR
    }



    public int getId() {
        return id;
    }

    public Doctor setId(int id) { // NOSONAR
        this.id = id;
        return this; // NOSONAR
    }


    public String getName() {
        return name;
    }


    public Doctor setName(String name) {// NOSONAR
        this.name = name;
        return this; // NOSONAR
    }

    public String getSpecialty() {
        return specialty;
    }


    public Doctor setSpecialty(String specialty) { // NOSONAR
        this.specialty = specialty;
        return this; // NOSONAR
    }

    public String getExperience() {
        return experience;
    }

    public Doctor setExperience(String experience) { // NOSONAR
        this.experience = experience;
        return this; // NOSONAR
    }

    public String getEmail() {
        return email; // NOSONAR
    }


    public Doctor setEmail(String email) { // NOSONAR
        this.email = email;
        return this; // NOSONAR
    }


    public String getPhone () {
        return phone;
    }


    public Doctor setPhone (String phone){ // NOSONAR
        this.phone = phone;
        return this; // NOSONAR
    }

    public Doctor setPassword (String password){ // NOSONAR
        this.password = password;
        return this; // NOSONAR


    }
}
