package com.gj.reactive.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface IHeartBeatChecker {

	public Mono<ServerResponse> getServicsStatus(ServerRequest request);
	public Mono<ServerResponse> getGreetingMessage(ServerRequest request);
}
