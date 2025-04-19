package com.temp.mapper;

import com.temp.dto.DoctorDto;
import com.temp.dto.DoctorCreateDto;
import com.temp.model.Doctor;
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
