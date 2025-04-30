package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.HealthProfileDto;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
class HealthProfileDtoTest {

    @Test
    void testConstructorAndGetters() {

        Long expectedId = 1L;
        Long expectedPatientId = 101L;
        double expectedHeight = 5.9;
        double expectedWeight = 75.5;
        String expectedBloodGroup = "O+";
        List<String> expectedAllergies = Arrays.asList("Peanuts", "Dust");
        String expectedMedicalHistory = "Asthma";
        String expectedConditions = "High Blood Pressure";

        HealthProfileDto healthProfile = new HealthProfileDto();
        healthProfile.setId(expectedId);
        healthProfile.setPatientId(expectedPatientId);
        healthProfile.setHeight(expectedHeight);
        healthProfile.setWeight(expectedWeight);
        healthProfile.setBloodGroup(expectedBloodGroup);
        healthProfile.setAllergies(expectedAllergies);
        healthProfile.setMedicalHistory(expectedMedicalHistory);
        healthProfile.setConditions(expectedConditions);

        assertEquals(expectedId, healthProfile.getId());
        assertEquals(expectedPatientId, healthProfile.getPatientId());
        assertEquals(expectedHeight, healthProfile.getHeight());
        assertEquals(expectedWeight, healthProfile.getWeight());
        assertEquals(expectedBloodGroup, healthProfile.getBloodGroup());
        assertEquals(expectedAllergies, healthProfile.getAllergies());
        assertEquals(expectedMedicalHistory, healthProfile.getMedicalHistory());
        assertEquals(expectedConditions, healthProfile.getConditions());
    }

    @Test
    void testSetters() {
        HealthProfileDto healthProfile = new HealthProfileDto();

        healthProfile.setId(1L);
        healthProfile.setPatientId(101L);
        healthProfile.setHeight(5.9);
        healthProfile.setWeight(75.5);
        healthProfile.setBloodGroup("O+");
        healthProfile.setAllergies(Arrays.asList("Peanuts", "Dust"));
        healthProfile.setMedicalHistory("Asthma");
        healthProfile.setConditions("High Blood Pressure");

        assertEquals(1L, healthProfile.getId());
        assertEquals(101L, healthProfile.getPatientId());
        assertEquals(5.9, healthProfile.getHeight());
        assertEquals(75.5, healthProfile.getWeight());
        assertEquals("O+", healthProfile.getBloodGroup());
        assertEquals(Arrays.asList("Peanuts", "Dust"), healthProfile.getAllergies());
        assertEquals("Asthma", healthProfile.getMedicalHistory());
        assertEquals("High Blood Pressure", healthProfile.getConditions());
    }

    @Test
    void testHealthProfileWithDifferentValues() {
        HealthProfileDto healthProfile = new HealthProfileDto();

        healthProfile.setId(2L);
        healthProfile.setPatientId(102L);
        healthProfile.setHeight(6.1);
        healthProfile.setWeight(85.0);
        healthProfile.setBloodGroup("A-");
        healthProfile.setAllergies(Arrays.asList("Shellfish", "Pollen"));
        healthProfile.setMedicalHistory("No issues");
        healthProfile.setConditions("None");

        assertEquals(2L, healthProfile.getId());
        assertEquals(102L, healthProfile.getPatientId());
        assertEquals(6.1, healthProfile.getHeight());
        assertEquals(85.0, healthProfile.getWeight());
        assertEquals("A-", healthProfile.getBloodGroup());
        assertEquals(Arrays.asList("Shellfish", "Pollen"), healthProfile.getAllergies());
        assertEquals("No issues", healthProfile.getMedicalHistory());
        assertEquals("None", healthProfile.getConditions());
    }
}
