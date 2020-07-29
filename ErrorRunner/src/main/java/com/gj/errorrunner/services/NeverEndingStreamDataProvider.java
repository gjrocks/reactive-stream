package com.gj.errorrunner.services;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.gj.errorrunner.entity.dto.ErrorDTO;

import reactor.core.publisher.Flux;

@Service  //this can be @component 
public class NeverEndingStreamDataProvider {

	Flux<ErrorDTO> neverEnding; 
	@PostConstruct
	public void init() {
		neverEnding=Flux.interval(Duration.ofSeconds(1)).share()
				   .map(val->
				    {
				    	//System.out.println("Val :" +val);
				        return new ErrorDTO("errorr : "+val, val, "tranactionID:"+val);
				        });
	}
	public Flux<ErrorDTO> getNeverEnding() {
		return neverEnding;
	}
	public void setNeverEnding(Flux<ErrorDTO> neverEnding) {
		this.neverEnding = neverEnding;
	}
	
	
}
