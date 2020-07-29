package com.gj.reactive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gj.reactive.entity.Person;
import com.gj.reactive.repos.IMapRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/annotation")
public class PersonAnnotationController {

	@Autowired
	IMapRepository<Person, Integer> ipersonRepos;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/person")
	public Flux<Person> getPeople() {
  System.out.println("called");
		return ipersonRepos.getAllAsFlux();
	}
	
	
}
