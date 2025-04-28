package com.smart.health.care.management.system.dto;

import java.util.List;

public class HealthProfileDto {
    private Long id;
    private Long patientId;
    private double height;
    private double weight;
    private String bloodGroup;
    private List<String> allergies;
    private String medicalHistory;
    private String conditions;

    public HealthProfileDto() {
        //constructor
    }

    // Manual Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    // Manual Builder Pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long patientId;
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

        public Builder patientId(Long patientId) {
            this.patientId = patientId;
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

        public HealthProfileDto build() {
            HealthProfileDto dto = new HealthProfileDto();
            dto.setId(this.id);
            dto.setPatientId(this.patientId);
            dto.setHeight(this.height);
            dto.setWeight(this.weight);
            dto.setBloodGroup(this.bloodGroup);
            dto.setAllergies(this.allergies);
            dto.setMedicalHistory(this.medicalHistory);
            dto.setConditions(this.conditions);
            return dto;
        }
    }
}
