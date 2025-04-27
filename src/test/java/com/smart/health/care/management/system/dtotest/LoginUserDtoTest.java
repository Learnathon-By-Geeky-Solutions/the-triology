package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.LoginUserDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginUserDtoTest {

    @Test
    void testPhoneNumberSetterAndGetter() {
        LoginUserDto dto = new LoginUserDto();
        dto.setPhoneNumber("01712345678");
        assertEquals("01712345678", dto.getPhoneNumber(), "Phone number should match the set value");
    }

    @Test
    void testPasswordSetterAndGetter() {
        LoginUserDto dto = new LoginUserDto();
        dto.setPassword("password123");
        assertEquals("password123", dto.getPassword(), "Password should match the set value");
    }

    @Test
    void testChainingSetters() {
        LoginUserDto dto = new LoginUserDto()
                .setPhoneNumber("01787654321")
                .setPassword("mySecret");

        assertAll(
                () -> assertEquals("01787654321", dto.getPhoneNumber(), "Phone number should match the chained set value"),
                () -> assertEquals("mySecret", dto.getPassword(), "Password should match the chained set value")
        );
    }
}