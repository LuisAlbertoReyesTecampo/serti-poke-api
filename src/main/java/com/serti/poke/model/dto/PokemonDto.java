package com.serti.poke.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PokemonDto {
	
	private int id;
	private String name;
	private int height;
	private PastTypesDto pastTypes;
	private SpritesDto sprites;
	private int weight;
	private List<AbilitiesDto> ablities;
	private SpeciesDto especies;
	private PokemonSpecieDto specieDto;
	private PokemonEvolutionDto evolutionDto;

}
