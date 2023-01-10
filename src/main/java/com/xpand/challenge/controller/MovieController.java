package com.xpand.challenge.controller;

import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.exception.RestExceptionHandler;
import com.xpand.challenge.service.MovieService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    private final RestExceptionHandler restExceptionHandler;

    public MovieController(MovieService movieService, RestExceptionHandler restExceptionHandler) {
        this.movieService = movieService;
        this.restExceptionHandler = restExceptionHandler;
    }

    @GetMapping
    public ResponseEntity<?> getMovies() {
        return ResponseEntity.ok().body(movieService.getMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(movieService.getMovie(id));
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<?> getMoviesByDate(@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok().body(movieService.getMoviesByDate(date));
    }

    @GetMapping("/cast/{id}")
    public ResponseEntity<?> getActoresFromMovie(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getActoresFromMovie(id));
    }

    @PostMapping(value = ("/create"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMovie(@RequestBody MovieDTO movieDTO) {
        try {
            movieService.createMovie(movieDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        try {
            movieService.updateMovie(id, movieDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            } else if (e.getClass().equals(IllegalArgumentException.class)) {
                return restExceptionHandler.badRequest();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }
}


