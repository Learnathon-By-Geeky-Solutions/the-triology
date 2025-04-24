package com.smart.health.care.management.system.model;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Doctor")
@SuppressWarnings("all")
public class Doctor implements UserDetails {

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

    @Column(nullable = false)
    private String password;

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
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public int getId() {
        return id;
    }

    public Doctor setId(int id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }


    public Doctor setName(String name) {
        this.name = name;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }


    public Doctor setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public String getExperience() {
        return experience;
    }

    public Doctor setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public String getEmail() {
        return email;
    }


    public Doctor setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getPhone () {
        return phone;
    }


    public Doctor setPhone (String phone){
        this.phone = phone;
        return this;
    }

    public Doctor setPassword (String password){
        this.password = password;
        return this;


    }
}
