package com.xpand.challenge.dto;

import com.xpand.challenge.model.Gender;

import java.time.LocalDate;

public class ActorSummary {
    private String name;
    private LocalDate birthdate;
    private Gender gender;

    public ActorSummary(String name, LocalDate birthdate, Gender gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
