package com.xpand.challenge.controller;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.exception.RestExceptionHandler;
import com.xpand.challenge.service.ActorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {
    private final ActorService actorService;

    private final RestExceptionHandler restExceptionHandler;

    public ActorController(ActorService actorService, RestExceptionHandler restExceptionHandler) {
        this.actorService = actorService;
        this.restExceptionHandler = restExceptionHandler;
    }

    @GetMapping
    public ResponseEntity<?> getActors() {
        return ResponseEntity.ok().body(actorService.getActors());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getActor(@RequestParam(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(actorService.getActor(id));
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @PostMapping(value = ("/create"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
        try {
            actorService.createActor(actorDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        try {
            actorService.updateActor(id, actorDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Long id) {
        try {
            actorService.deleteActor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage().equals("NOT FOUND")) {
                return restExceptionHandler.handleNotFound();
            }
            return restExceptionHandler.handleException(e);
        }
    }
}
