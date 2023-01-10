package com.xpand.challenge.service;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorSummary;
import com.xpand.challenge.dto.IdentifiableActorDTO;

import java.util.List;

public interface ActorService {

    IdentifiableActorDTO createActor(ActorDTO actorDTO);

    ActorSummary getActor(Long id);

    List<ActorSummary> getActors();

    void updateActor(Long id, ActorDTO actorDTO);

    void deleteActor(Long id);
}
