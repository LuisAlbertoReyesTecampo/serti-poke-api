package com.serti.poke.model.dao;

import javax.persistence.Entity;


import lombok.Data;

@Data
@Entity
public class PokemonEvolution {

	private int id;
	private ChainEvolution species;
}
