package com.serti.poke.model.dao;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PokemonSpecie {

	private String baseHappiness;
	private String captureRate;
	private ColorPokemon color;
	private EvolutionChain evolution;
}
