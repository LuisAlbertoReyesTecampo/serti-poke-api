package com.serti.poke.service;

import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;

public interface PokeService {
	
	PokemonDto getPokemonById(int id);
	PokemonSpecieDto getPokemonSpecieById(int id);
	PokemonEvolutionDto getEvolutionByUrl(String url);

}
