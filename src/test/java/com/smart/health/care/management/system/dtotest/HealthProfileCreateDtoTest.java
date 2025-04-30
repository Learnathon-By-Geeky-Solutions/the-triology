package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealthProfileCreateDtoTest {

    @Test
    void testHealthProfileCreateDto() {

        HealthProfileCreateDto healthProfileCreateDto = new HealthProfileCreateDto();
        Long expectedPatientId = 123L;
        double expectedHeight = 5.9;
        double expectedWeight = 70.5;
        String expectedBloodGroup = "O+";
        List<String> expectedAllergies = Arrays.asList("Peanuts", "Dust");
        String expectedMedicalHistory = "No major surgeries.";
        String expectedConditions = "Asthma";

        healthProfileCreateDto.setPatientId(expectedPatientId);
        healthProfileCreateDto.setHeight(expectedHeight);
        healthProfileCreateDto.setWeight(expectedWeight);
        healthProfileCreateDto.setBloodGroup(expectedBloodGroup);
        healthProfileCreateDto.setAllergies(expectedAllergies);
        healthProfileCreateDto.setMedicalHistory(expectedMedicalHistory);
        healthProfileCreateDto.setConditions(expectedConditions);

        assertEquals(expectedPatientId, healthProfileCreateDto.getPatientId());
        assertEquals(expectedHeight, healthProfileCreateDto.getHeight());
        assertEquals(expectedWeight, healthProfileCreateDto.getWeight());
        assertEquals(expectedBloodGroup, healthProfileCreateDto.getBloodGroup());
        assertEquals(expectedAllergies, healthProfileCreateDto.getAllergies());
        assertEquals(expectedMedicalHistory, healthProfileCreateDto.getMedicalHistory());
        assertEquals(expectedConditions, healthProfileCreateDto.getConditions());
    }
}
