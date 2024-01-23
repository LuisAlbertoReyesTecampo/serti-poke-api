package com.serti.poke.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Pokemon {

	@Id
	private int id;
	@Column
	private String name;
	@Column
	private int height;
	@Column
	private String generation;
	@Column
	private String sprites;
}
