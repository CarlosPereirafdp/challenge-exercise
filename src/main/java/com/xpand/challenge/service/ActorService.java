package com.xpand.challenge.service;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.IdentifiableActorDTO;

import java.util.List;

public interface ActorService {

    IdentifiableActorDTO createActor(ActorDTO actorDTO);
    IdentifiableActorDTO getActor(Long id);
    List<IdentifiableActorDTO> getActors();

    void updateActor(Long id, ActorDTO actorDTO);
    void deleteActor(Long id);
}
