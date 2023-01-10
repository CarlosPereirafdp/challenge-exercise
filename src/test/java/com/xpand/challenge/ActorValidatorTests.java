package com.xpand.challenge;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.model.Gender;
import com.xpand.challenge.validator.Validator;
import com.xpand.challenge.validator.impl.ActorValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class ActorValidatorTests {

    static Validator<ActorDTO> validator;

    @BeforeAll
    static void setup() {
        validator = new ActorValidator();
    }

    @Test
    void doTestValidActor(){
        ActorDTO dto = new ActorDTO();
        dto.setName("Carlos");
        dto.setBirthdate(LocalDate.parse("1994-04-28"));
        dto.setGender(Gender.MALE);
        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void doTestInvalidName(){
        ActorDTO dto = new ActorDTO();
        dto.setBirthdate(LocalDate.parse("1994-04-28"));
        dto.setGender(Gender.MALE);
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }
    @Test
    void doTestInvalidBirthdate(){
        ActorDTO dto = new ActorDTO();
        dto.setName("Carlos");
        dto.setGender(Gender.MALE);
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }
    @Test
    void doTestInvlaidGender(){
        ActorDTO dto = new ActorDTO();
        dto.setName("Carlos");
        dto.setBirthdate(LocalDate.parse("1994-04-28"));
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }
}
