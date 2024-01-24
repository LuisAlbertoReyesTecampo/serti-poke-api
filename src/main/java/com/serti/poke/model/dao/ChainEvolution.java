package com.serti.poke.model.dao;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ChainEvolution {

	private List<Species> especies; 
}
