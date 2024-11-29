package com.serti.poke.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.serti.poke.model.dto.AbilitiesDto;
import com.serti.poke.model.dto.ChainEvolutionDto;
import com.serti.poke.model.dto.ColorPokemonDto;
import com.serti.poke.model.dto.EvolutionChainDto;
import com.serti.poke.model.dto.PastTypesDto;
import com.serti.poke.model.dto.PokemonDto;
import com.serti.poke.model.dto.PokemonEvolutionDto;
import com.serti.poke.model.dto.PokemonSpecieDto;
import com.serti.poke.model.dto.SpeciesDto;
import com.serti.poke.model.dto.SpritesDto;

@Component
public class PokemonHelperInfo {
	private static final Logger logger = LogManager.getLogger(PokemonHelperInfo.class);

	public PokemonDto getBasicInfo(JsonNode root, PokemonDto pokemon) {
		logger.info("getBasicInfo");
		JsonNode name = root.path("name");
		String namePoke = name.asText();
		pokemon.setName(namePoke);
		JsonNode height = root.path("height");
		int heightPoke = height.asInt();
		pokemon.setHeight(heightPoke);
		JsonNode weight = root.path("weight");
		int weightPoke = weight.asInt();
		pokemon.setWeight(weightPoke);
		logger.debug("Obtencion de informacion a dto informacion basica");
	
	return pokemon;
	}
	
	public PokemonDto getAbilities(JsonNode root, PokemonDto pokemon) {
		logger.info("getAbilities");
		JsonNode abilities = root.path("abilities");
		List<JsonNode> abilitiesPoke = abilities.findValues("ability");
		List<AbilitiesDto> abilitiesList = new ArrayList<>();
		for(JsonNode node : abilitiesPoke) {
			logger.debug("Listado de habilidades");
			String nameAbilitie = node.findValue("name").asText();
			String urlAbilitie = node.findValue("url").asText();
			AbilitiesDto abilitiesDto = new AbilitiesDto(nameAbilitie,urlAbilitie);
			abilitiesList.add(abilitiesDto);
			logger.debug("Obtencion correctad de listado");
		}
		pokemon.setAblities(abilitiesList);
		logger.debug("Obtencion de informacion a dto Abitlites");
	return pokemon;
	}
	
	public PokemonDto getPastTypes(JsonNode root, PokemonDto pokemon) {
		logger.info("getPastTypes");
		JsonNode pastTypes = root.path("past_types");
		JsonNode pastTypesPoke;
		if (pastTypes.findValue("generation") != null) {
			logger.debug("valida si existe nodo generation");
			pastTypesPoke = pastTypes.findValue("generation");
			JsonNode generation = pastTypesPoke.findValue("name");
			PastTypesDto pastTypesDto = new PastTypesDto(generation.asText());
			pokemon.setPastTypes(pastTypesDto);
			logger.debug("Obtencion de nodo generation");
		}
		logger.debug("Obtencion de informacion a dto PastTypes");
	return pokemon;
	}
	
	public PokemonDto getSprites(JsonNode root, PokemonDto pokemon) {
		logger.info("getSprites");
		JsonNode sprites = root.path("sprites");
		JsonNode spritesPoke = sprites.findValue("front_default");
		SpritesDto spritesUrl = new SpritesDto(spritesPoke.asText());
		pokemon.setSprites(spritesUrl);
		logger.debug("Consumo de api Poke");
	return pokemon;
	}
	
	public PokemonDto getSpecies(JsonNode root, PokemonDto pokemon) {
		logger.debug("Consumo de api Poke");
		JsonNode species = root.path("species");
		SpeciesDto speciesDto = new SpeciesDto(species.findValue("name").asText(),species.findValue("url").asText());
		pokemon.setEspecies(speciesDto);
		logger.debug("Obtencion de informacion a dto Species");
		
		return pokemon;
	}
	
	
	public PokemonSpecieDto getBasicInfo(JsonNode root,PokemonSpecieDto specieDto) {
		logger.info("getBasicInfo");
		JsonNode baseHappiness = root.path("base_happiness");
		specieDto.setBaseHappiness(baseHappiness.asText());
		JsonNode capRate = root.path("capture_rate");
		specieDto.setCaptureRate(capRate.asText());
		logger.debug("Obtencion de informacion a dto Basic info specie");
		
		return specieDto;
	}
	public PokemonSpecieDto getColor(JsonNode root,PokemonSpecieDto specieDto) {
		logger.info("getColor");
		JsonNode color = root.path("color");
		ColorPokemonDto colorDto = new ColorPokemonDto(color.findValue("name").asText(), color.findValue("url").asText());
		specieDto.setColor(colorDto);
		logger.debug("Obtencion de informacion a dto Color");
		return specieDto;
	}
	public PokemonSpecieDto getevolutionChain(JsonNode root,PokemonSpecieDto specieDto) {
		logger.info("getEvolutionChain");
		JsonNode color = root.path("evolution_chain");
		EvolutionChainDto evolutionChainDto = new EvolutionChainDto(color.findValue("url").asText());
		specieDto.setEvolution(evolutionChainDto);
		logger.debug("Obtencion de informacion a dto Evolution chain");
		return specieDto;
	}

	public PokemonEvolutionDto getEvolution(JsonNode root, PokemonEvolutionDto evolutionDto) {
		logger.info("getEvolution");
		JsonNode id = root.path("id");
		evolutionDto.setId(id.asInt());
		JsonNode chain = root.path("chain");
		List<SpeciesDto> speciesDtos = new ArrayList<>();
		logger.debug("Obtencion de evolucion");
		JsonNode sp = chain.get("species");
		SpeciesDto speciesDto = new SpeciesDto(sp.findValue("name").asText(), sp.findValue("url").asText());
		speciesDtos.add(speciesDto);
		while(chain.has("evolves_to")) {
			logger.debug("Recuperando los diferentes niveles de evolucion");
			JsonNode chain1 = chain.path("evolves_to");
			if(chain1.size()>0) {
				logger.debug("Validando tamanio...");
				JsonNode sp1 = chain1.get(0);
				JsonNode sp2 = sp1.get("species");
				SpeciesDto evolvesTo = new SpeciesDto(sp2.findValue("name").asText(), sp2.findValue("url").asText());
				speciesDtos.add(evolvesTo);
				chain = chain1.get(0);
			}else {
				logger.debug("Obtencion de informacion a dto Evolucion pokemon");
				break;
			}
		}
		
		ChainEvolutionDto chainEvol = new ChainEvolutionDto(speciesDtos);
		evolutionDto.setSpeciesDto(chainEvol);
		logger.debug("Finalizando ...");
		return evolutionDto;
	}
}
