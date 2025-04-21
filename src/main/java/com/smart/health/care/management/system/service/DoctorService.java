package com.smart.health.care.management.system.service;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.DoctorDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.DoctorMapper;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.repository.DoctorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }



    public String addDoctor(DoctorCreateDto doctorCreateDto) {
        validateDoctorCreateDto(doctorCreateDto);
        Doctor doctor = doctorMapper.toEntity(doctorCreateDto);
        doctorRepo.save(doctor);
        return "Doctor added successfully";
    }
    public List<DoctorDto> getAllDoctors() {
        return doctorRepo.findAll().stream().map(doctorMapper::toDto).toList();
    }

    private static final String DOCID= "Doctor with ID ";
    private static final String FOUND= " not found";
    public DoctorDto getDoctorById(int id) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DOCID + id + FOUND));
        return doctorMapper.toDto(doctor);

    }

    public String updateDoctor(int id, DoctorCreateDto doctorCreateDto) {
        validateDoctorCreateDto(doctorCreateDto);
        Doctor existingdoctor= doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DOCID + id + FOUND));
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
                .orElseThrow(() -> new ResourceNotFoundException(DOCID + id + FOUND));
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
