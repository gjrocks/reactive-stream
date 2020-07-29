package com.gj.reactive.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.gj.reactive.entity.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientTest {
 //single webclient can be used is it thread safe?
	
	private WebClient client=WebClient.create("http://localhost:9000"); //base url
	
	//@Test
	public void greeting() {
	Mono<ClientResponse> resp=client.get()
		      .uri("/greet")
		      .accept(MediaType.TEXT_PLAIN)
		      .exchange();
		
	assertThat(resp).isNotNull();
	String body=resp.flatMap(res->res.bodyToMono(String.class)).block(Duration.ofSeconds(3));
	assertThat(body).isNotNull().isNotEmpty().isEqualToIgnoringCase("Hello!");
	}
	
	//@Test
	public void reactivePerson() throws Exception {
		Flux<Person> resp=client.get()
			      .uri("/personreactive")
			      .accept(MediaType.TEXT_EVENT_STREAM)
			      .retrieve()
			       .bodyToFlux(Person.class);
			
		assertThat(resp).isNotNull();
		resp.subscribe(t->{
			System.out.println("ganesh :" +t);
			
		});
		
		Thread.sleep(5000);
		//String body=resp.flatMap(res->res.bodyToMono(String.class)).block(Duration.ofSeconds(3));
		//assertThat(body).isNotNull().isNotEmpty().isEqualToIgnoringCase("Hello!");
		}
	
	
	@Test
	public void reactivePerson_annotation() throws Exception {
		Flux<Person> resp=client.get()
			      .uri("/annotation/person")
			      .accept(MediaType.TEXT_EVENT_STREAM)
			      .retrieve()
			       .bodyToFlux(Person.class);
			
		assertThat(resp).isNotNull();
		resp.subscribe(t->{
			System.out.println("ganesh :" +t);
			
		});
		
		Thread.sleep(5000);
		//String body=resp.flatMap(res->res.bodyToMono(String.class)).block(Duration.ofSeconds(3));
		//assertThat(body).isNotNull().isNotEmpty().isEqualToIgnoringCase("Hello!");
		}
}
