package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.DTO.DoctorCreateDto;
import com.Smart.Health.Care.Management.System.DTO.DoctorDto;
import com.Smart.Health.Care.Management.System.Exception.BusinessLogicException;
import com.Smart.Health.Care.Management.System.Exception.InvalidInputException;
import com.Smart.Health.Care.Management.System.Exception.ResourceNotFoundException;
import com.Smart.Health.Care.Management.System.Mapper.DoctorMapper;
import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorMapper doctorMapper;

    public String addDoctor(DoctorCreateDto doctorCreateDto) {
        validateDoctorCreateDto(doctorCreateDto);
        Doctor doctor = doctorMapper.toEntity(doctorCreateDto);
        doctorRepo.save(doctor);
        return "Doctor added successfully";
    }
    public List<DoctorDto> getAllDoctors() {
        return doctorRepo.findAll().stream().map(doctorMapper::toDto).collect(Collectors.toList());
    }
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + id + " not found"));
        return doctorMapper.toDto(doctor);

    }

    public String updateDoctor(int id, DoctorCreateDto doctorCreateDto) {
        validateDoctorCreateDto(doctorCreateDto);
        Doctor existingdoctor= doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + id + " not found"));
        existingdoctor.setName(doctorCreateDto.getDoctorName());
        existingdoctor.setSpecialty(doctorCreateDto.getDoctorSpeciality());
        existingdoctor.setExperience(doctorCreateDto.getDoctorExperience());
        existingdoctor.setPhone(doctorCreateDto.getDoctorPhone());
        existingdoctor.setEmail(doctorCreateDto.getDoctorEmail());

        doctorRepo.save(existingdoctor);
        return "Doctor updated successfully";
    }
    public String deleteDoctor(int id) {
        Doctor doc = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + id + " not found"));
        doctorRepo.delete(doc);
        return "Doctor deleted successfully";
    }



    private void validateDoctorCreateDto(DoctorCreateDto dto) {
        if (dto.getDoctorName() == null || dto.getDoctorName().trim().isEmpty()) {
            throw new InvalidInputException("Doctor name cannot be empty.");
        }
        if (dto.getDoctorPhone() == null || dto.getDoctorPhone().trim().length() < 11) {
            throw new InvalidInputException("Phone number must be at least 11 digits.");
        }
        if (dto.getDoctorEmail() == null || dto.getDoctorEmail().trim().isEmpty()) {
            throw new BusinessLogicException("Email cannot be empty.");
        }
        else{
            String email = dto.getDoctorEmail().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                throw new BusinessLogicException("Invalid email format.");
            }
        }
        if(dto.getDoctorExperience() == null || dto.getDoctorExperience().trim().isEmpty()) {
            throw new BusinessLogicException("Experience cannot be empty.");
        }
        if(dto.getDoctorSpeciality()==null || dto.getDoctorSpeciality().trim().isEmpty()) {
            throw new BusinessLogicException("Speciality cannot be empty.");
        }
    }
}
