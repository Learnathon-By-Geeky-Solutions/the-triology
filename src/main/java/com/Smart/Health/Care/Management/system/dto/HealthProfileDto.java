package com.smart.Health.Care.Management.system.dto;

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



    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getPatientId(){
        return patientId;
    }
    public void setPatientId(Long patientId){
        this.patientId = patientId;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies(){
        return allergies;
    }
    public void setAllergies(List<String> allergies){
        this.allergies = allergies;
    }

    public String getMedicalHistory(){
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory){
        this.medicalHistory = medicalHistory;
    }
    public String getConditions(){
        return conditions;
    }
    public void setConditions(String conditions){
        this.conditions = conditions;
    }

}
