package com.temp.service;

import com.temp.dto.DoctorCreateDto;
import com.temp.dto.DoctorDto;
import com.temp.exception.BusinessLogicException;
import com.temp.exception.InvalidInputException;
import com.temp.exception.ResourceNotFoundException;
import com.temp.mapper.DoctorMapper;
import com.temp.model.Doctor;
import com.temp.repository.DoctorRepo;
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
        existingdoctor.setSpecialty(doctorCreateDto.getDoctorspeciality());
        existingdoctor.setExperience(doctorCreateDto.getDoctorexperience());
        existingdoctor.setPhone(doctorCreateDto.getDoctorphone());
        existingdoctor.setEmail(doctorCreateDto.getDoctoremail());

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
        if (dto.getDoctorphone() == null || dto.getDoctorphone().trim().length() < 11) {
            throw new InvalidInputException("Phone number must be at least 11 digits.");
        }
        if (dto.getDoctoremail() == null || dto.getDoctoremail().trim().isEmpty()) {
            throw new BusinessLogicException("Email cannot be empty.");
        }
        else{
            String email = dto.getDoctoremail().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                throw new BusinessLogicException("Invalid email format.");
            }
        }
        if(dto.getDoctorexperience() == null || dto.getDoctorexperience().trim().isEmpty()) {
            throw new BusinessLogicException("Experience cannot be empty.");
        }
        if(dto.getDoctorspeciality()==null || dto.getDoctorspeciality().trim().isEmpty()) {
            throw new BusinessLogicException("Speciality cannot be empty.");
        }
    }
}
