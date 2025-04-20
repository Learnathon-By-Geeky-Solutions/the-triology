package com.Smart.Health.Care.Management.System.Mapper;

import com.Smart.Health.Care.Management.System.DTO.DoctorDto;
import com.Smart.Health.Care.Management.System.DTO.DoctorCreateDto;
import com.Smart.Health.Care.Management.System.Model.Doctor;
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
