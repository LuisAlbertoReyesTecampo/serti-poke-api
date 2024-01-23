package com.serti.poke.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

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

	public PokemonDto getBasicInfo(JsonNode root, PokemonDto pokemon) {
		JsonNode name = root.path("name");
		String namePoke = name.asText();
		System.out.println("Name "+namePoke);
		pokemon.setName(namePoke);
		
		JsonNode height = root.path("height");
		int heightPoke = height.asInt();
		System.out.println("Height "+heightPoke);
		pokemon.setHeight(heightPoke);
		
		JsonNode weight = root.path("weight");
		int weightPoke = weight.asInt();
		System.out.println("Weight "+weightPoke);
		pokemon.setWeight(weightPoke);
	
	return pokemon;
	}
	
	public PokemonDto getAbilities(JsonNode root, PokemonDto pokemon) {
		JsonNode abilities = root.path("abilities");
		List<JsonNode> abilitiesPoke = abilities.findValues("ability");
		System.out.print("Abilities");
		List<AbilitiesDto> abilitiesList = new ArrayList<>();
		for(JsonNode node : abilitiesPoke) {
			String nameAbilitie = node.findValue("name").asText();
			String urlAbilitie = node.findValue("url").asText();
			AbilitiesDto abilitiesDto = new AbilitiesDto(nameAbilitie,urlAbilitie);
			abilitiesList.add(abilitiesDto);
		}
		pokemon.setAblities(abilitiesList);
	return pokemon;
	}
	
	public PokemonDto getPastTypes(JsonNode root, PokemonDto pokemon) {
		JsonNode pastTypes = root.path("past_types");
		JsonNode pastTypesPoke;
		if (pastTypes.findValue("generation") != null) {
			pastTypesPoke = pastTypes.findValue("generation");
			JsonNode generation = pastTypesPoke.findValue("name");
			PastTypesDto pastTypesDto = new PastTypesDto(generation.asText());
			System.out.println("Past types "+pastTypesDto.getGeneration());
			pokemon.setPastTypes(pastTypesDto);
		}
	return pokemon;
	}
	
	public PokemonDto getSprites(JsonNode root, PokemonDto pokemon) {
		JsonNode sprites = root.path("sprites");
		JsonNode spritesPoke = sprites.findValue("front_default");
		SpritesDto spritesUrl = new SpritesDto(spritesPoke.asText());
		System.out.println("Sprites "+spritesUrl.getSprites());
		pokemon.setSprites(spritesUrl);
	return pokemon;
	}
	
	public PokemonDto getSpecies(JsonNode root, PokemonDto pokemon) {
		JsonNode species = root.path("species");
		SpeciesDto speciesDto = new SpeciesDto(species.findValue("name").asText(),species.findValue("url").asText());
		pokemon.setEspecies(speciesDto);
		
		return pokemon;
	}
	
	
	public PokemonSpecieDto getBasicInfo(JsonNode root,PokemonSpecieDto specieDto) {
		JsonNode baseHappiness = root.path("base_happiness");
		specieDto.setBaseHappiness(baseHappiness.asText());
		JsonNode capRate = root.path("capture_rate");
		specieDto.setCaptureRate(capRate.asText());
		
		return specieDto;
	}
	public PokemonSpecieDto getColor(JsonNode root,PokemonSpecieDto specieDto) {
		JsonNode color = root.path("color");
		ColorPokemonDto colorDto = new ColorPokemonDto(color.findValue("name").asText(), color.findValue("url").asText());
		specieDto.setColor(colorDto);
		return specieDto;
	}
	public PokemonSpecieDto getevolutionChain(JsonNode root,PokemonSpecieDto specieDto) {		
		JsonNode color = root.path("evolution_chain");
		EvolutionChainDto evolutionChainDto = new EvolutionChainDto(color.findValue("url").asText());
		specieDto.setEvolution(evolutionChainDto);
		return specieDto;
	}

	public PokemonEvolutionDto getEvolution(JsonNode root, PokemonEvolutionDto evolutionDto) {
		JsonNode id = root.path("id");
		evolutionDto.setId(id.asInt());
		JsonNode chain = root.path("chain");
		List<SpeciesDto> speciesDtos = new ArrayList<>();
		JsonNode sp = chain.get("species");
		
		
		//String name = sp.findValue("name").asText();
		
		SpeciesDto speciesDto = new SpeciesDto(sp.findValue("name").asText(), sp.findValue("url").asText());
		speciesDtos.add(speciesDto);
		
		while(chain.has("evolves_to")) {
			System.out.println("chain "+chain.toString());
			JsonNode chain1 = chain.path("evolves_to");
			if(chain1.size()>0) {
				JsonNode sp1 = chain1.get(0);
				JsonNode sp2 = sp1.get("species");
				SpeciesDto evolvesTo = new SpeciesDto(sp2.findValue("name").asText(), sp2.findValue("url").asText());
				speciesDtos.add(evolvesTo);
				chain = chain1.get(0);
				System.out.println("chain "+chain.toString());
			}else 
				break;
		}
		
		ChainEvolutionDto chainEvol = new ChainEvolutionDto(speciesDtos);
		evolutionDto.setSpeciesDto(chainEvol);
		return evolutionDto;
	}
}
