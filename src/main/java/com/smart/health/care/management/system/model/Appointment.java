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
    private LocalDate date;  // ✅ Change LocalDateTime → LocalDate

    @Column(nullable = false)
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime time;  // ✅ Change LocalDateTime → LocalTime

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, Doctor doctor, LocalDate date, LocalTime time) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    // Getters and Setters
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

    // Placeholder method for getting patient's name.
    // This method is currently not implemented because the 'Patient' class should provide the patient's name.
    // If you want to implement this, you should:
    // 1. Retrieve the patient's name from the Patient object (assuming the Patient class has a 'getName()' method).
    // 2. If a 'getName()' method exists in the Patient class, this method can simply return patient.getName().
    // 3. Otherwise, you should create a 'getName()' method in the Patient class to access the patient's name.
    public boolean getPatientName() {
        throw new UnsupportedOperationException("Method not implemented. Implement this to return patient's name.");
    }
}
