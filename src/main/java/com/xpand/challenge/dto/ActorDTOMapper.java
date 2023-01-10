package com.xpand.challenge.dto;

import com.xpand.challenge.model.Actor;

import java.util.Optional;

public class ActorDTOMapper {
    public static IdentifiableActorDTO toActorDTO(Actor actor){
        return Optional.ofNullable(actor).map(m -> {
            IdentifiableActorDTO dto = new IdentifiableActorDTO();
            dto.setId(actor.getId());
            dto.setName(actor.getName());
            dto.setBirthdate(actor.getBirthdate());
            dto.setGender(actor.getGender());
            dto.setMovie(actor.getMovie());
            return dto;
        }).orElse(null);
    }

    public static Actor fromActorDTO(ActorDTO dto){
        return Optional.ofNullable(dto).map(d -> {
            Actor actor = new Actor();
            actor.setName(d.getName());
            actor.setBirthdate(d.getBirthdate());
            actor.setGender(d.getGender());
            actor.setMovie(d.getMovie());
            return actor;
        }).orElse(null);
    }
}
