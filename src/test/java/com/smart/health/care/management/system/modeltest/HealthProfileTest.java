package com.smart.health.care.management.system.modeltest;

import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealthProfileTest {

    @Test
    void testHealthProfileFieldsAndMethods() {

        Patient patient = new Patient();
        patient.setId(1L);

        List<String> allergies = Arrays.asList("Peanuts", "Dust");

        HealthProfile profile = new HealthProfile();
        profile.setId(100L);
        profile.setPatient(patient);
        profile.setHeight(175.5);
        profile.setWeight(70.2);
        profile.setBloodGroup("O+");
        profile.setAllergies(allergies);
        profile.setMedicalHistory("Asthma");
        profile.setConditions("Diabetes");

        assertEquals(100L, profile.getId());
        assertEquals(patient, profile.getPatient());
        assertEquals(175.5, profile.getHeight());
        assertEquals(70.2, profile.getWeight());
        assertEquals("O+", profile.getBloodGroup());
        assertEquals(allergies, profile.getAllergies());
        assertEquals("Asthma", profile.getMedicalHistory());
        assertEquals("Diabetes", profile.getConditions());
        assertEquals(1L, profile.getPatientId());
    }

    @Test
    void testGetPatientIdWhenPatientIsNull() {

        HealthProfile profile = new HealthProfile();
        profile.setPatient(null);

        assertEquals(-1L, profile.getPatientId()); // As you defined for null patient
    }

    @Test
    void testHealthProfileBuilder() {

        Patient patient = new Patient();
        patient.setId(2L);

        List<String> allergies = Arrays.asList("Pollen");

        HealthProfile profile = new HealthProfile.Builder()
                .id(200L)
                .patient(patient)
                .height(165.0)
                .weight(60.5)
                .bloodGroup("A-")
                .allergies(allergies)
                .medicalHistory("None")
                .conditions("Healthy")
                .build();

        assertEquals(200L, profile.getId());
        assertEquals(patient, profile.getPatient());
        assertEquals(165.0, profile.getHeight());
        assertEquals(60.5, profile.getWeight());
        assertEquals("A-", profile.getBloodGroup());
        assertEquals(allergies, profile.getAllergies());
        assertEquals("None", profile.getMedicalHistory());
        assertEquals("Healthy", profile.getConditions());
        assertEquals(2L, profile.getPatientId());
    }
}
