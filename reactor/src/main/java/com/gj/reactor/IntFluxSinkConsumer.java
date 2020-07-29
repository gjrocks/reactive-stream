package com.gj.reactor;

import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;

public class IntFluxSinkConsumer<T> implements Consumer<FluxSink<Integer>> {

	private FluxSink<Integer> lSink;

	@Override
	public void accept(FluxSink<Integer> t) {
		lSink = t;
	}

	public void sink(int data) {
		this.lSink.next(data);
	}
}
