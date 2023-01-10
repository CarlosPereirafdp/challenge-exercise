package com.xpand.challenge.dto;

import com.xpand.challenge.model.Gender;

import java.time.LocalDate;

public class ActorSummary {

    private Long id;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String movie;


    public ActorSummary(Long id, String name, LocalDate birthdate, Gender gender, String movie) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
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
