package com.xpand.challenge;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorDTOMapper;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActorMapperTests {
    @Test
    void doTestFromActorDTO() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Carlos");
        dto.setBirthdate(LocalDate.parse("1994-04-28"));
        dto.setGender(Gender.MALE);
        Actor actor = ActorDTOMapper.fromActorDTO(dto);
        assertNotNull(actor);
        assertEquals(dto.getName(), actor.getName());
        assertEquals(dto.getBirthdate(), actor.getBirthdate());
        assertEquals(dto.getGender(), actor.getGender());
    }

    @Test
    void doTestToActorDTO() {
        Actor actor = new Actor();
        actor.setId(1L);
        actor.setName("Carlos");
        actor.setBirthdate(LocalDate.parse("1994-04-28"));
        actor.setGender(Gender.MALE);
        IdentifiableActorDTO dto = ActorDTOMapper.toActorDTO(actor);
        assertNotNull(dto);
        assertEquals(actor.getId(), dto.getId());
        assertEquals(actor.getName(), dto.getName());
        assertEquals(actor.getBirthdate(), dto.getBirthdate());
        assertEquals(actor.getGender(), dto.getGender());
    }
}
