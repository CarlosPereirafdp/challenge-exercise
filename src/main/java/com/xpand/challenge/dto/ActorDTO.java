package com.xpand.challenge.dto;

import com.xpand.challenge.model.Gender;
import com.xpand.challenge.model.Movie;

import java.time.LocalDate;

public class ActorDTO {

    private String name;
    private LocalDate birthdate;
    private Gender gender;

    private Movie movie;


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
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
