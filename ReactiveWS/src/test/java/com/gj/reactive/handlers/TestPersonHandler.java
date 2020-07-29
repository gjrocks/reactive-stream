package com.gj.reactive.handlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gj.reactive.entity.Person;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@SpringBootTest
public class TestPersonHandler {

	@MockBean
	ServerRequest request;

	 	
	@BeforeEach
	public void init() {
		when(request.bodyToMono(Person.class)).thenReturn(Mono.just(new Person()));
	}
	
	@Test
	public void testPersonHandler() {
   
		 IPersonHandler handler=new PersonHandler();
		 //=null;
		 Mono<ServerResponse> person=handler.createPerson(request);
		 assertThat(person).isNotNull();
		 StepVerifier.create(person)
		              .consumeNextWith(obj->{
		            	//  Person personObj=obj.
		            	//  System.out.println(personObj.getId());
		            	  assertThat(obj).as("Server response object is present").isNotNull();
		            	  assertThat(obj.statusCode()).as("Server response is persisted").isNotNull().isEqualTo(201);
		            	  })
		              .expectComplete()
		              .verify();
	}
}
