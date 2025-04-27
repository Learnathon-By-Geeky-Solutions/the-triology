package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.RegisterUserDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserDtoTest {

    @Test
    void testSettersAndGetters() {
        RegisterUserDto dto = new RegisterUserDto();

        LocalDate dob = LocalDate.of(1998, 5, 15);

        dto.setName("Alice")
                .setPhoneNumber("01711111111")
                .setPassword("securePass123")
                .setDateOfBirth(dob);

        assertAll(
                () -> assertEquals("Alice", dto.getName(), "Name should match"),
                () -> assertEquals("01711111111", dto.getPhoneNumber(), "Phone number should match"),
                () -> assertEquals("securePass123", dto.getPassword(), "Password should match"),
                () -> assertEquals(dob, dto.getDateOfBirth(), "Date of birth should match")
        );
    }

    @Test
    void testDateFormatPattern() {
        LocalDate dob = LocalDate.parse("15/05/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        RegisterUserDto dto = new RegisterUserDto()
                .setDateOfBirth(dob);

        assertEquals(1998, dto.getDateOfBirth().getYear(), "Year should be 1998");
        assertEquals(5, dto.getDateOfBirth().getMonthValue(), "Month should be May (5)");
        assertEquals(15, dto.getDateOfBirth().getDayOfMonth(), "Day should be 15");
    }
}