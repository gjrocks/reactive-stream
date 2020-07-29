package com.gj.errorRunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gj.errorrunner.services.NeverEndingStreamDataProvider;

import reactor.core.scheduler.Schedulers;

@SpringBootTest
class ErrorRunnerApplicationTests {

	@Autowired
	NeverEndingStreamDataProvider neverEndingStreamDataProvider;
	@Test
	void contextLoads() throws Exception{
		Assertions.assertNotNull(neverEndingStreamDataProvider);
		neverEndingStreamDataProvider.getNeverEnding().subscribeOn(Schedulers.elastic()).subscribe(v->{
			System.out.println("1Thread running :"+ Thread.currentThread().getName()+ ": Value :" +v);
		});
		
		Thread.sleep(5000);
		
		neverEndingStreamDataProvider.getNeverEnding().subscribeOn(Schedulers.elastic()).subscribe(v->{
			System.out.println("2Thread running :"+ Thread.currentThread().getName()+ ": Value :" +v);
		});
		Thread.sleep(50000);
	}

}
