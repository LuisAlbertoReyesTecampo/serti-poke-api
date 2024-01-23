package com.serti.poke.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChainEvolutionDto {
	
	private List<SpeciesDto> especies; 

}
