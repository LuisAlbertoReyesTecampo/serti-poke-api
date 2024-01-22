package com.serti.poke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Api(value="prueba")
@RequestMapping(value="/api-get")
public class PruebaController {
	
	@RequestMapping(value="/prueba-get", method=RequestMethod.GET)
	public String prueba() {
		return "Hello World";
	}

}
