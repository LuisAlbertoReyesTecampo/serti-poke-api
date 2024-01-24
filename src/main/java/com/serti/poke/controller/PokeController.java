package com.serti.poke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;
import com.serti.poke.service.PokeService;

@RestController
@RequestMapping(value="/pokemon")
public class PokeController {
	
	@Autowired
	private PokeService service;

	@RequestMapping(value="/getPokemon/{id}", method=RequestMethod.GET)
	public ResponseEntity<PokemonDto> getPokemon(@PathVariable int id) {
		PokemonDto pokemonDto = service.getPokemonById(id);
		PokemonSpecieDto specieDto = service.getPokemonSpecieById(id);
		PokemonEvolutionDto evolutionDto = service.getEvolutionByUrl(specieDto.getEvolution().getUrl());
		pokemonDto.setSpecieDto(specieDto);
		pokemonDto.setEvolutionDto(evolutionDto);
		return new ResponseEntity<>(pokemonDto, HttpStatus.OK);
	}
}
