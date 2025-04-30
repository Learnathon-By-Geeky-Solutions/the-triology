package com.smart.health.care.management.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentCreateDto {

    private Long patientId;
    private int doctorId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "hh:mm a")
    private LocalTime time;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
