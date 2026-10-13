package com.example.dragonballapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dragonballapi.entity.TvCharacter;
import com.example.dragonballapi.repository.TvCharacterRepository;

@Service
public class TvCharacterService {

    private final TvCharacterRepository repository;

    public TvCharacterService(TvCharacterRepository repository) {
        this.repository = repository;
    }

    public List<TvCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public Optional<TvCharacter> getCharacterById(long id) {
        return repository.findById(id);
    }

    public TvCharacter addCharacter(TvCharacter character) {
        return repository.save(character);
    }

    public TvCharacter updateCharacter(long id, TvCharacter character) {
        character.setCharacterId(id);
        return repository.save(character);
    }

    public void deleteCharacter(long id) {
        repository.deleteById(id);
    }

    public List<TvCharacter> getCharactersByUniverse(String universe) {
        return repository.findByUniverseIgnoreCase(universe);
    }

    public List<TvCharacter> searchCharactersByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}