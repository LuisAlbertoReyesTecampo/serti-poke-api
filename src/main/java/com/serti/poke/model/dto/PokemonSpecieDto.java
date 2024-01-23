package com.serti.poke.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonSpecieDto {

	private String baseHappiness;
	private String captureRate;
	private ColorPokemonDto color;
	private EvolutionChainDto evolution;
}
