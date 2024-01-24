package com.serti.poke;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.serti.poke.controller.PokeController;
import com.serti.poke.model.dto.AbilitiesDto;
import com.serti.poke.model.dto.ChainEvolutionDto;
import com.serti.poke.model.dto.ColorPokemonDto;
import com.serti.poke.model.dto.EvolutionChainDto;
import com.serti.poke.model.dto.PastTypesDto;
import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;
import com.serti.poke.model.dto.SpeciesDto;
import com.serti.poke.model.dto.SpritesDto;
import com.serti.poke.service.PokeService;

@ExtendWith(MockitoExtension.class)
class PokeControllerMockitoTest {
	
	@Mock
	private PokeService pokeService;

	@Test
	void whenPokemonsId_thenRetrivedInfo() {
		Mockito.when(pokeService.getPokemonById(1)).thenReturn(
				new PokemonDto(1, "bulbasaur", 7, null, 
						new SpritesDto("\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png\""), 69, 
						Arrays.asList(new AbilitiesDto("overgrow","\"https://pokeapi.co/api/v2/ability/65/\""),
								new AbilitiesDto("chlorophyll", "\"https://pokeapi.co/api/v2/ability/34/\"")) ,
						new SpeciesDto("bulbasaur", "\"https://pokeapi.co/api/v2/pokemon-species/1/\""),
						new PokemonSpecieDto("50", "45", new ColorPokemonDto("green", "\"https://pokeapi.co/api/v2/pokemon-color/5/\""),
								new EvolutionChainDto("\"https://pokeapi.co/api/v2/evolution-chain/1/\"")),
						new PokemonEvolutionDto(1, new ChainEvolutionDto(Arrays.asList(new SpeciesDto("bulbasaur", "\"https://pokeapi.co/api/v2/pokemon-species/1/\""),
								new SpeciesDto("ivysaur", "\"https://pokeapi.co/api/v2/pokemon-species/2/\""),new SpeciesDto("venasaur", "\"https://pokeapi.co/api/v2/pokemon-species/3/\""))))));
		
		PokemonDto actual = pokeService.getPokemonById(1);
		
		
		String expectedName = "bulbasaur";
		List<SpeciesDto> expectedSpecies = Arrays.asList(
				new SpeciesDto("bulbasaur", "\"https://pokeapi.co/api/v2/pokemon-species/1/\""),
				new SpeciesDto("ivysaur", "\"https://pokeapi.co/api/v2/pokemon-species/2/\""),
				new SpeciesDto("venasaur", "\"https://pokeapi.co/api/v2/pokemon-species/3/\""));
		
		assertAll(
				()->assertEquals(expectedName, actual.getName()),
				()->assertArrayEquals(expectedSpecies.toArray(), actual.getEvolutionDto().getSpeciesDto().getEspecies().toArray())
				);
	
	}
	
	
	@Test
	void whenPokemonSpeciesId_thenRetrivedInfo() {
		Mockito.when(pokeService.getPokemonSpecieById(1)).thenReturn(
				new PokemonSpecieDto("50", "45", new ColorPokemonDto("green", "\"https://pokeapi.co/api/v2/pokemon-color/5/\""),
								new EvolutionChainDto("\"https://pokeapi.co/api/v2/evolution-chain/1/\"")));
		
		PokemonSpecieDto  actual = pokeService.getPokemonSpecieById(1);
		
		
		PokemonSpecieDto expected = new PokemonSpecieDto("50", "45", new ColorPokemonDto("green", "\"https://pokeapi.co/api/v2/pokemon-color/5/\""), new EvolutionChainDto("\"https://pokeapi.co/api/v2/evolution-chain/1/\""));
		
		assertEquals(expected, actual);
	
	}
	
	@Test
	void whenPokemonEvolutionUrl_thenRetrivedInfo() {
		Mockito.when(pokeService.getEvolutionByUrl("https://pokeapi.co/api/v2/evolution-chain/1")).thenReturn(
				new PokemonEvolutionDto(1, new ChainEvolutionDto(Arrays.asList(new SpeciesDto("bulbasaur", "\"https://pokeapi.co/api/v2/pokemon-species/1/\""),
						new SpeciesDto("ivysaur", "\"https://pokeapi.co/api/v2/pokemon-species/2/\""),new SpeciesDto("venasaur", "\"https://pokeapi.co/api/v2/pokemon-species/3/\"")))));
		
		PokemonEvolutionDto  actual = pokeService.getEvolutionByUrl("https://pokeapi.co/api/v2/evolution-chain/1");
		
		
		PokemonEvolutionDto expected = new PokemonEvolutionDto(1, new ChainEvolutionDto(Arrays.asList(new SpeciesDto("bulbasaur", "\"https://pokeapi.co/api/v2/pokemon-species/1/\""),
				new SpeciesDto("ivysaur", "\"https://pokeapi.co/api/v2/pokemon-species/2/\""),new SpeciesDto("venasaur", "\"https://pokeapi.co/api/v2/pokemon-species/3/\""))));
		
		assertEquals(expected, actual);
	
	}
}
