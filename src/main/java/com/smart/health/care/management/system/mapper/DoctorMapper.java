package com.smart.health.care.management.system.mapper;

import com.smart.health.care.management.system.dto.DoctorDto;
import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    //Map Entity -> DTO (For Response)
    public DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(doctor.getId(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getExperience()
                );
    }

    //Map DTO -> Entity (For Saving/Updating)
    public Doctor toEntity(DoctorCreateDto dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getDoctorName());
        doctor.setSpecialty(dto.getDoctorSpeciality());
        doctor.setExperience(dto.getDoctorExperience());
        doctor.setEmail(dto.getDoctorEmail());
        doctor.setPhone(dto.getDoctorPhone());
        doctor.setPassword(dto.getDoctorPassword());
        return doctor;
    }
}
