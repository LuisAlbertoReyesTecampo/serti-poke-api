package com.serti.poke.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.serti.poke.model.dao.Pokemon;
import com.serti.poke.model.dto.PokemonDto;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
	
	PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);
	
	//Pokemon toEntity(PokemonDto dto);
	//PokemonDto toDto(Pokemon dao);

}
