package com.gj.reactive.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gj.reactive.entity.Person;
import com.gj.reactive.repos.IMapRepository;

import reactor.core.publisher.Mono;

@Service("personHandler")
public class PersonHandler implements IPersonHandler {

	@Autowired
	@Qualifier("personMapRepository")
	IMapRepository<Person, Integer> iPersonRepository;
	
	public Mono<ServerResponse> processPerson(final Person pObj) {
		Person pObj2=iPersonRepository.createEntity(pObj);
		return ServerResponse.status(HttpStatus.CREATED).bodyValue(pObj2);
	}

	@Override
	public Mono<ServerResponse> createPerson(ServerRequest request) {
		Mono<Person> person=request.bodyToMono(Person.class);
		 //Person p=person.block(); //blocking call started
		//  p.setId(id);
		 
		//return person.map(pObj-> {pObj.setId(getRandomPositiveNumber()); return pObj;});
		return person.flatMap(this::processPerson);
		
	}

	@Override
	public Mono<ServerResponse> getPeople(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(iPersonRepository.getAll());
	}

	@Override
	public Mono<ServerResponse> getPeopleReactive(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(iPersonRepository.getAllAsFlux(),Person.class);
	}

}
