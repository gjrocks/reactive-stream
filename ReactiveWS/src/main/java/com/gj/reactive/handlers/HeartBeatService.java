package com.gj.reactive.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Service("heartBeatService")
public class HeartBeatService implements IHeartBeatChecker{

	@Override
	public Mono<ServerResponse> getServicsStatus(ServerRequest request) {
	    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
	      .body(BodyInserters.fromValue("OK!"));
	  }

	@Override
	public Mono<ServerResponse> getGreetingMessage(ServerRequest request) {
		 return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			      .body(BodyInserters.fromValue("Hello!"));
	}

	

}
