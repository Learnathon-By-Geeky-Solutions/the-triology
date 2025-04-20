package com.temp.dto;

public class DoctorDto {
    private int id;
    private String docName;
    private String docSpeciality;
    private String docExperience;
    /*private String docEmail;
    private String docPhone;
     private String doctorPassword;

     */


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
    /*
    public String getDocEmail() {
        return docEmail;
    }
    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }
    public String getDocphone() {
        return docPhone;
    }
    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }
    public String getDoctorPassword() {
        return doctorPassword;
    }
    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

     */


}
