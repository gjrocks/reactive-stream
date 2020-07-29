package com.gj.reactor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

//@SpringBootTest
class ReactorApplicationTests {

	@Test
	void testFlux() {
		StepVerifier.create(getNumbers(3))
		             .expectNext(1,2,3)
		              .expectComplete()
		              .verify();
	}

	public Flux<Integer> getNumbers(int count){
		return Flux.range(1, count);
	}
	
	@Test
	void testFluxWithAssertJ() {
		StepVerifier.create(getNumbers(3))
		             .consumeNextWith(num->assertThat(num).as("First Number").isEqualTo(1))
		             .consumeNextWith(num->assertThat(num).as("Second Number").isEqualTo(2))
		             .consumeNextWith(num->assertThat(num).as("Third Number").isEqualTo(3))
		             .expectComplete()
		             .verify();
		              
	}
}
