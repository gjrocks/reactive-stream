package com.gj.reactor;

import java.time.Duration;
import java.util.stream.IntStream;

import antlr.collections.impl.IntRange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * @author cqktss0
 *
 */
public class FluxSinkDemo {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
	
		demoFluxSink();
	 Thread.sleep(100000);
	}

	private static void demoFluxSink() {

		final IntFluxSinkConsumer<FluxSink<Integer>> k=new IntFluxSinkConsumer<FluxSink<Integer>>();
      Flux<Integer> intFlux=Flux.create(k)
    		                    .delayElements(Duration.ofSeconds(1))
    		                    .log()
    		                    .share();
      intFlux.delayElements(Duration.ofSeconds(2)).subscribe(PrintData::printData);
      IntStream.range(1,100)
               .forEach(data->k.sink(data));
	}
}
