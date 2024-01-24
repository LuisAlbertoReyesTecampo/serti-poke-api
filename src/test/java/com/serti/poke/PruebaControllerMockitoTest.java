package com.serti.poke;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.serti.poke.controller.PokeController;
import com.serti.poke.controller.PruebaController;
import com.serti.poke.service.PokeService;

@ExtendWith(MockitoExtension.class)
class PruebaControllerMockitoTest {
	
	

	@InjectMocks
	private PruebaController pruebaController;
	
	@Test
	public void shouldReturnMessage() {
		String prueba = pruebaController.prueba();
		assertThat(prueba).isEqualTo("Hello World");
	}

}
