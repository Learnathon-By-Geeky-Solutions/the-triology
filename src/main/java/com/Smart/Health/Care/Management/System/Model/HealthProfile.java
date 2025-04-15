package com.Smart.Health.Care.Management.System.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "health_profile")
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = true)
    private String bloodGroup;

    @ElementCollection
    @CollectionTable(name = "health_profile_allergies", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "allergy")
    private List<String> allergies;

    @Column(nullable = true)
    private String medicalHistory;

    @Column(nullable = true)
    private String conditions;

    // Default constructor
    public HealthProfile() {
    }

    public HealthProfile(Long id, Patient patient, double height, double weight, String bloodGroup,
                         List<String> allergies, String medicalHistory, String conditions) {
        this.id = id;
        this.patient = patient;
        this.height = height;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.medicalHistory = medicalHistory;
        this.conditions = conditions;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}