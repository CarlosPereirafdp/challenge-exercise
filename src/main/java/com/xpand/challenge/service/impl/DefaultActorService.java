package com.xpand.challenge.service.impl;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.ActorDTOMapper;
import com.xpand.challenge.dto.ActorSummary;
import com.xpand.challenge.dto.IdentifiableActorDTO;
import com.xpand.challenge.exception.ActorServiceException;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.repository.ActorRepository;
import com.xpand.challenge.service.ActorService;
import com.xpand.challenge.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultActorService implements ActorService {
    private final ActorRepository actorRepository;
    private final Validator<ActorDTO> actorDTOValidator;

    public DefaultActorService(ActorRepository actorRepository, Validator<ActorDTO> actorDTOValidator) {
        this.actorRepository = actorRepository;
        this.actorDTOValidator = actorDTOValidator;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public IdentifiableActorDTO createActor(ActorDTO actorDTO) {
        actorDTOValidator.validate(actorDTO);
        Actor actor = ActorDTOMapper.fromActorDTO(actorDTO);
        return ActorDTOMapper.toActorDTO(actorRepository.save(actor));
    }

    @Override
    public ActorSummary getActor(Long id) {
        IdentifiableActorDTO actorDTO = actorRepository.findById(id)
                .map(ActorDTOMapper::toActorDTO)
                .orElseThrow(ActorServiceException::actorNotFound);
        return new ActorSummary(actorDTO.getId(), actorDTO.getName(),actorDTO.getBirthdate(),actorDTO.getGender(),actorDTO.getMovie().getTitle());
    }

    @Override
    public List<ActorSummary> getActors() {
        List<ActorSummary> actors = new ArrayList<>();
        List<IdentifiableActorDTO> actorDTOS = actorRepository.findAll().stream()
                .map(ActorDTOMapper::toActorDTO)
                .collect(Collectors.toList());

        for (IdentifiableActorDTO a : actorDTOS) {
            ActorSummary actorSummary = new ActorSummary(a.getId(), a.getName(), a.getBirthdate(), a.getGender(), a.getMovie().getTitle());
            actors.add(actorSummary);
        }
        return actors;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateActor(Long id, ActorDTO actorDTO) {
        actorRepository.findById(id).orElseThrow(ActorServiceException::actorNotFound);
        actorDTOValidator.validate(actorDTO);
        Actor actor = ActorDTOMapper.fromActorDTO(actorDTO);
        actor.setId(id);
        actorRepository.save(actor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
