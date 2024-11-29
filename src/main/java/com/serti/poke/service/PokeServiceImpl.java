package com.serti.poke.service;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serti.poke.controller.PokeController;
import com.serti.poke.helper.PokemonHelperInfo;
import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;

@Service
public class PokeServiceImpl implements PokeService{
	private static final Logger logger = LogManager.getLogger(PokeServiceImpl.class);
	
	@Autowired
	private PokemonHelperInfo helperInfo;

	@Override
	public PokemonDto getPokemonById(int id) {
		logger.info("getPokemonById");
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://pokeapi.co/api/v2/pokemon/";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + id, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonDto pokemon = new PokemonDto();
		try {
			logger.debug("Consumo de api Poke");
			logger.debug("Mapeo de informacion recuperada");
			root = mapper.readTree(response.getBody());
			pokemon.setId(id);
			pokemon = helperInfo.getBasicInfo(root, pokemon);
			pokemon = helperInfo.getAbilities(root, pokemon);
			pokemon = helperInfo.getPastTypes(root, pokemon);
			pokemon = helperInfo.getSprites(root, pokemon);
			pokemon = helperInfo.getSpecies(root, pokemon);
			logger.debug("Mapeo finalizado");
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return pokemon;
	}
	
	@Override
	public PokemonSpecieDto getPokemonSpecieById(int id) {
		logger.info("getPokemonSpecieById");
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://pokeapi.co/api/v2/pokemon-species/";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + id, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonSpecieDto specieDto = new PokemonSpecieDto();
		try {
			logger.debug("Consumo de api Poke specie");
			root = mapper.readTree(response.getBody());
			specieDto = helperInfo.getBasicInfo(root,specieDto);
			specieDto = helperInfo.getColor(root,specieDto);
			specieDto = helperInfo.getevolutionChain(root,specieDto);
			logger.debug("Mapeo finalizado");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return specieDto;
	}

	@Override
	public PokemonEvolutionDto getEvolutionByUrl(String url) {
		logger.info("getEvolutionByUrl");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		PokemonEvolutionDto evolutionDto = new PokemonEvolutionDto();
		
		try {
			logger.debug("Mapeo evolucion pokemon");
			root = mapper.readTree(response.getBody());
			evolutionDto = helperInfo.getEvolution(root,evolutionDto);
			logger.debug("Mapeo finalizado");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return evolutionDto;
	}
	
	

	

}
