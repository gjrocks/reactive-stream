package com.gj.errorRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

 
//sanity test check the logs it loads all the application context and it does not start the server.
@SpringBootTest
@AutoConfigureMockMvc
public class ErrorRunnerRestController_WithOutServerStart_HttpRequestTest {

	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testController() throws Exception{
		
		this.mock.perform(get("/restway/1"))
	             .andDo(print())
	             .andExpect(status().isOk())
		         .andExpect(content().string(containsString("ganesh")));                 
		
	}
}
