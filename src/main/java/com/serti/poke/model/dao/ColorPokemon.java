package com.serti.poke.model.dao;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ColorPokemon {

	private String name;
	private String url;
}
