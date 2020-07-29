package com.gj.reactive.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface IPersonHandler {

	public Mono<ServerResponse> createPerson(ServerRequest request);
    public Mono<ServerResponse> getPeople(ServerRequest request);
    public Mono<ServerResponse> getPeopleReactive(ServerRequest request);
}
