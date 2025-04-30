package com.smart.health.care.management.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(nullable = false)
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime time;

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, Doctor doctor, LocalDate date, LocalTime time) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}
