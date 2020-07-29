package com.gj.reactive.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gj.reactive.handlers.IPersonHandler;

@Configuration
@EnableWebFlux
public class PersonRouters implements WebFluxConfigurer {

	@Autowired
	@Qualifier("personHandler")
	IPersonHandler iPersonHandler;

	@Bean
	RouterFunction<ServerResponse> route() {

		return RouterFunctions.route().path("/person", builder ->

		builder.POST("/", RequestPredicates.accept(MediaType.APPLICATION_JSON), iPersonHandler::createPerson).GET("/",
				RequestPredicates.accept(MediaType.APPLICATION_JSON), iPersonHandler::getPeople)

		).build();

	}

	@Bean
	RouterFunction<ServerResponse> route2() {

		return RouterFunctions.route()
				.path("/personreactive", builder -> builder.GET("/",
						RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM), iPersonHandler::getPeopleReactive))
				.build();

	}
}
