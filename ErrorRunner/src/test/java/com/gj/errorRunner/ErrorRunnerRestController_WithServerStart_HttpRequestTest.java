package com.gj.errorRunner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
//following will start the application on random port. This is not just controller test 
//sanity test check the logs it loads all the application context and it starts the server.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ErrorRunnerRestController_WithServerStart_HttpRequestTest {

	@LocalServerPort
	int port;
	
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void testController() throws Exception {
	
		assertThat(this.testRestTemplate.getForObject("http://localhost:" + this.port +"/restway/1", String.class))
				.contains("ganesh");
		
		
	}
	
	
	
}
