package com.serti.poke.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serti.poke.helper.PokemonHelperInfo;
import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;

@Service
public class PokeServiceImpl implements PokeService{
	
	@Autowired
	private PokemonHelperInfo helperInfo;

	@Override
	public PokemonDto getPokemonById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://pokeapi.co/api/v2/pokemon/";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + id, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonDto pokemon = new PokemonDto();
		try {
			root = mapper.readTree(response.getBody());
			pokemon.setId(id);
			pokemon = helperInfo.getBasicInfo(root, pokemon);
			pokemon = helperInfo.getAbilities(root, pokemon);
			pokemon = helperInfo.getPastTypes(root, pokemon);
			pokemon = helperInfo.getSprites(root, pokemon);
			pokemon = helperInfo.getSpecies(root, pokemon);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Consumo existoso");
		return pokemon;
	}
	
	@Override
	public PokemonSpecieDto getPokemonSpecieById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://pokeapi.co/api/v2/pokemon-species/";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + id, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonSpecieDto specieDto = new PokemonSpecieDto();
		try {
			root = mapper.readTree(response.getBody());
			specieDto = helperInfo.getBasicInfo(root,specieDto);
			specieDto = helperInfo.getColor(root,specieDto);
			specieDto = helperInfo.getevolutionChain(root,specieDto);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return specieDto;
	}

	@Override
	public PokemonEvolutionDto getEvolutionByUrl(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonEvolutionDto evolutionDto = new PokemonEvolutionDto();
		
		try {
			root = mapper.readTree(response.getBody());
			evolutionDto = helperInfo.getEvolution(root,evolutionDto);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return evolutionDto;
	}
	
	

	

}
