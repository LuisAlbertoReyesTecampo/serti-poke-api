package com.serti.poke.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEvolutionDto {

	private int id;
	private ChainEvolutionDto speciesDto;
}
