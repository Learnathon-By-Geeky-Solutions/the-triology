package com.smart.health.care.management.system.dto;

public class DoctorDto {
    private int id;

    private String docName;
    private String docSpeciality;
    private String docExperience;


    //constructor
    public DoctorDto(int id, String docName, String docSpeciality, String docExperience) {
        this.id= id;
        this.docName = docName;
        this.docSpeciality = docSpeciality;
        this.docExperience = docExperience;






    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }
    public String getDocSpeciality() {
        return docSpeciality;
    }
    public void setDocSpeciality(String docSpeciality) {
        this.docSpeciality = docSpeciality;
    }
    public String getDocExperience() {
        return docExperience;
    }
    public void setDocExperience(String docExperience) {
        this.docExperience = docExperience;
    }


}
