package com.gj.reactive.repos;

import java.util.List;

import reactor.core.publisher.Flux;

public interface IMapRepository<T,K> {

	public T createEntity(T t);

	public T get(K i);

	public List<T> getAll();

	public Flux<T> getAllAsFlux();
}
