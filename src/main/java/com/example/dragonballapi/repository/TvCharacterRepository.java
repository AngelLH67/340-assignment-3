package com.example.dragonballapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dragonballapi.entity.TvCharacter;

public interface TvCharacterRepository extends JpaRepository<TvCharacter, Long> {

    List<TvCharacter> findByUniverseIgnoreCase(String universe);

    List<TvCharacter> findByNameContainingIgnoreCase(String name);
}