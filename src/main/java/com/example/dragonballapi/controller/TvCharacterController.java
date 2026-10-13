package com.example.dragonballapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dragonballapi.entity.TvCharacter;
import com.example.dragonballapi.service.TvCharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class TvCharacterController {

    private final TvCharacterService service;

    public TvCharacterController(TvCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<TvCharacter> getAllCharacters(
            @RequestParam(required = false) String universe,
            @RequestParam(required = false) String name) {

        if (universe != null) {
            return service.getCharactersByUniverse(universe);
        }

        if (name != null) {
            return service.searchCharactersByName(name);
        }

        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvCharacter> getCharacterById(
            @PathVariable long id) {

        return service.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TvCharacter> addCharacter(
            @Valid @RequestBody TvCharacter character) {

        TvCharacter savedCharacter = service.addCharacter(character);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvCharacter> updateCharacter(
            @PathVariable long id,
            @Valid @RequestBody TvCharacter character) {

        if (service.getCharacterById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TvCharacter updatedCharacter =
                service.updateCharacter(id, character);

        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(
            @PathVariable long id) {

        if (service.getCharacterById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteCharacter(id);

        return ResponseEntity.noContent().build();
    }
}