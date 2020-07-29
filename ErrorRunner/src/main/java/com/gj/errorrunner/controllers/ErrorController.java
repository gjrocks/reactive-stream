package com.gj.errorrunner.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gj.errorrunner.entity.dto.ErrorDTO;
import com.gj.errorrunner.services.NeverEndingStreamDataProvider;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/errors")
public class ErrorController {

	@Autowired
	NeverEndingStreamDataProvider neverEndingStreamDataProvider;
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	public Mono<ErrorDTO> getError(@PathVariable Integer id) {
		System.out.println("getError called :" +id);
		ErrorDTO dt=new ErrorDTO("Error : " +id, new Long(id), "Tranaction ID : "+id);
		
		return Mono.just(dt);
	}
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ErrorDTO> getErrors() {
		//System.out.println("getError called :" +id);
	 return   Flux.range(1, 5000).
	    map(val->new ErrorDTO("errorr : "+val, new Long(val), "tranactionID:"+val)).delayElements(Duration.ofSeconds(1));
	   //return neverEndingStreamDataProvider.getNeverEnding();
	    
		//ErrorDTO dt=new ErrorDTO("errorr : 1", 1, "tranactionID:"+1);
		//List<ErrorDTO> li=new ArrayList<>();
		//li.add(dt);
	//	return Flux.fromIterable(li);
	}
}


