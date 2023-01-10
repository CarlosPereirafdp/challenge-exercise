package com.xpand.challenge.validator.impl;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.model.Gender;
import com.xpand.challenge.validator.Validator;

import java.util.Optional;

public class ActorValidator implements Validator<ActorDTO> {
    @Override
    public void validate(ActorDTO actor) {
        Optional.ofNullable(actor.getName())
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Name must not be empty"));

        Optional.ofNullable(actor.getBirthdate())
                .orElseThrow(() -> new IllegalArgumentException("Birthdate must not be empty"));

        Optional.ofNullable(actor.getGender())
                .filter(gender -> gender == Gender.MALE || gender == Gender.FEMALE)
                .orElseThrow(() -> new IllegalArgumentException("Gender must be MALE or FEMALE"));
    }
}

