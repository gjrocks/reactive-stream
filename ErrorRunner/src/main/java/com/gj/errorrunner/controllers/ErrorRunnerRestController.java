package com.gj.errorrunner.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorRunnerRestController {

	
	@GetMapping("/restway/{id}")
	public String getRestway(@PathVariable int id) {
		return "ganesh";
	}
}
