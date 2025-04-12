package com.Smart.Health.Care.Management.System.DTO;

public class DoctorDto {
    private int id;
    private String docname;
    private String docspeciality;
    private String docexperience;
    private String docemail;
    private String docphone;


    //constructor
    public DoctorDto(int id, String docname, String docspeciality, String docexperience) {
        this.id= id;
        this.docname = docname;
        this.docspeciality = docspeciality;
        this.docexperience = docexperience;
        this.docemail = docemail;
        this.docphone = docphone;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDocname() {
        return docname;
    }
    public void setDocname(String docname) {
        this.docname = docname;
    }
    public String getDocspeciality() {
        return docspeciality;
    }
    public void setDocspeciality(String docspeciality) {
        this.docspeciality = docspeciality;
    }
    public String getDocexperience() {
        return docexperience;
    }
    public void setDocexperience(String docexperience) {
        this.docexperience = docexperience;
    }
    public String getDocemail() {
        return docemail;
    }
    public void setDocemail(String docemail) {
        this.docemail = docemail;
    }
    public String getDocphone() {
        return docphone;
    }
    public void setDocphone(String docphone) {
        this.docphone = docphone;
    }



}
