package com.gj.reactive.repos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gj.reactive.entity.Person;

import reactor.core.publisher.Flux;

@Service("personMapRepository")
public class PersonRepository implements IMapRepository<Person,Integer> {

	private static Random r = new Random(1000L);

	public static int getRandomPositiveNumber() {
		int id = r.nextInt();
		if (id < 0)
			id = id * -1;
		return id;

	}

	private static Map<Integer, Person> mp = new HashMap<>();
	static {
		mp.put(1, new Person("admin"));
	}

	@Override
	public Person createEntity(Person t) {
		Integer id = t.getId();
		if (id == null || id == 0) {
			id = getRandomPositiveNumber();
		}
		t.setId(id);
		mp.put(id, t);
		return t;
	}

	@Override
	public Person get(Integer i) {
		
		return mp.get(i);
	}

	@Override
	public List<Person> getAll() {
		return new ArrayList<Person>(mp.values());
	}

	@Override
	public Flux<Person> getAllAsFlux() {
		return Flux.fromStream(mp.values().stream());
	}

}
