package com.serti.poke.model.dao;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Species {

	private String name;
	private String url;
}
