package com.serti.poke;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PokeControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;
	

	@Test
	void shouldReturnPokemon() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/getPokemon/{id}",1))
		.andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
}
