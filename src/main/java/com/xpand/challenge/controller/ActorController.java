package com.xpand.challenge.controller;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.service.ActorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {
  private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<?> getActors() {
        return ResponseEntity.ok().body(actorService.getActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActor(@PathVariable Long id) {
        return ResponseEntity.ok().body(actorService.getActor(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
        actorService.createActor(actorDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        actorService.updateActor(id, actorDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
