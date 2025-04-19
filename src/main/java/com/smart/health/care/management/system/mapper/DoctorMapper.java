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
        doctor.setSpecialty(dto.getDoctorspeciality());
        doctor.setExperience(dto.getDoctorexperience());
        doctor.setEmail(dto.getDoctoremail());
        doctor.setPhone(dto.getDoctorphone());
        return doctor;
    }
}
