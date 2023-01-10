package com.xpand.challenge.service.impl;

import com.xpand.challenge.dto.ActorSummary;
import com.xpand.challenge.dto.IdentifiableMovieDTO;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.MovieDTOMapper;
import com.xpand.challenge.exception.MovieServiceException;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.MovieService;
import com.xpand.challenge.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final Validator<MovieDTO> movieValidator;

    public DefaultMovieService(MovieRepository movieRepository, Validator<MovieDTO> movieValidator) {
        this.movieRepository = movieRepository;
        this.movieValidator = movieValidator;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public IdentifiableMovieDTO createMovie(MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        Movie movie = MovieDTOMapper.fromMovieDTO(movieDTO);
        return MovieDTOMapper.toMovieDTO(movieRepository.save(movie));
    }

    @Override
    public IdentifiableMovieDTO getMovie(Long id) {
        return movieRepository.findById(id)
                .map(MovieDTOMapper::toMovieDTO)
                .orElseThrow(MovieServiceException::movieNotFound);
    }

    @Override
    public List<IdentifiableMovieDTO> getMovies() {
        return movieRepository.findAll().stream()
                .map(MovieDTOMapper::toMovieDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActorSummary> getActoresFromMovie(Long id) {
        List<ActorSummary> actors = new ArrayList<>();
        Movie movie = movieRepository.findById(id).orElseThrow(MovieServiceException::movieNotFound);

        for (Actor a : movie.getActors()) {
            ActorSummary actorSummary = new ActorSummary(a.getId(), a.getName(), a.getBirthdate(), a.getGender(), movie.getTitle());
            actors.add(actorSummary);
        }
        return actors;
    }

    @Override
    public List<IdentifiableMovieDTO> getMoviesByDate(LocalDate date) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getDate().isEqual(date))
                .map(MovieDTOMapper::toMovieDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMovie(Long id, MovieDTO movieDTO) {
        movieRepository.findById(id).orElseThrow(MovieServiceException::movieNotFound);
        movieValidator.validate(movieDTO);
        Movie movie = MovieDTOMapper.fromMovieDTO(movieDTO);
        movie.setId(id);
        movieRepository.save(movie);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}
