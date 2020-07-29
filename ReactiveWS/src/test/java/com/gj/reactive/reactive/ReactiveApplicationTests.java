package com.gj.reactive.reactive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.gj.reactive.entity.Person;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReactiveApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void greetEndPoint_GET() {
		webTestClient.get()
		             .uri("/greet")
		             .accept(MediaType.TEXT_PLAIN)
		             .exchange()
		             .expectStatus().isOk()
		             .expectBody(String.class).isEqualTo("Hello!");
		
	}
	
	@Test
	void personEndPoint_post() {
		webTestClient.post()
		             .uri("/person")
		             .accept(MediaType.APPLICATION_JSON)
		             .contentType(MediaType.APPLICATION_JSON)
		             .body(BodyInserters.fromValue(new Person("ganesh"))) 
		             .exchange()
		             .expectStatus().isCreated()
		             .expectBody(Person.class)
		             .consumeWith(obj->{
		            	 Person p=obj.getResponseBody();
		            	 assertThat(p).as("The server response is not empty").isNotNull();
		            	 assertThat(p.getId()).as("The person object is created").isNotNull().isGreaterThan(0);
		             
		             });
		
	}
    
	//This is returning the list of person as part of mono, not right use of the reactive.
	@Test
	void personEndPoint_get_all() {
		personEndPoint_post(); //inserting 1 record
		webTestClient.get()
		             .uri("/person")
		             .accept(MediaType.APPLICATION_JSON)
		             .exchange()
		             .expectStatus().isOk()
		             .expectBodyList(Person.class)
		             .consumeWith(obj->{
		            	 List<Person> pList=obj.getResponseBody();
		            	 assertThat(pList).isNotNull().hasSizeGreaterThan(0);
		            	 
		             });
		             ;
		           
		
	}
	
	@Test
	void personEndPoint_get_all_reactive() {
		personEndPoint_post(); //inserting 1 record
		webTestClient.get()
		             .uri("/personreactive")
		             .accept(MediaType.TEXT_EVENT_STREAM)
		             .exchange()
		             .expectStatus().isOk()
		             .expectBodyList(Person.class);      
		             
		           
		
	}
}
