package com.smart.health.care.management.system.model;

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


    public HealthProfile() {
    }


    private HealthProfile(Builder builder) {
        this.id = builder.id;
        this.patient = builder.patient;
        this.height = builder.height;
        this.weight = builder.weight;
        this.bloodGroup = builder.bloodGroup;
        this.allergies = builder.allergies;
        this.medicalHistory = builder.medicalHistory;
        this.conditions = builder.conditions;
    }


    public static class Builder {
        private Long id;
        private Patient patient;
        private double height;
        private double weight;
        private String bloodGroup;
        private List<String> allergies;
        private String medicalHistory;
        private String conditions;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder bloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
            return this;
        }

        public Builder allergies(List<String> allergies) {
            this.allergies = allergies;
            return this;
        }

        public Builder medicalHistory(String medicalHistory) {
            this.medicalHistory = medicalHistory;
            return this;
        }

        public Builder conditions(String conditions) {
            this.conditions = conditions;
            return this;
        }

        public HealthProfile build() {
            return new HealthProfile(this);
        }
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