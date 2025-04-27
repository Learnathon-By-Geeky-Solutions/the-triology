package com.smart.health.care.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthProfileDto {
    private Long id;
    private Long patientId;
    private double height;
    private double weight;
    private String bloodGroup;
    private List<String> allergies;
    private String medicalHistory;
    private String conditions;
}
