package com.serti.poke.model.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class Pokemon {

	@Id
	private int id;
	private String name;
	private int height;
	private PastTypes pastTypes;
	private Sprites sprites;
	private int weight;
	private List<Abilities> ablities;
	private Species especies;
	private PokemonSpecie specie;
	private PokemonEvolution evolution;
}
