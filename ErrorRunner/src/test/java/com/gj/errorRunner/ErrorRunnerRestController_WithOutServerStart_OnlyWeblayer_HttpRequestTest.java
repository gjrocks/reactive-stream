package com.gj.errorRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.gj.errorrunner.controllers.ErrorRunnerRestController;

 
//sanity test check the logs it does not load all the application context and it does not start the server.
//it is the unit test for the controller/web layer
//for multiple controllers application provide the name of the controller here
//mock all the dependencies used in the controller
//this is unit test for the controllers
@WebMvcTest(ErrorRunnerRestController.class)
public class ErrorRunnerRestController_WithOutServerStart_OnlyWeblayer_HttpRequestTest {

	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testController() throws Exception{
		
		this.mock.perform(get("/restapi/error/1"))
	             .andDo(print())
	             .andExpect(status().isOk())
		         .andExpect(content().string(containsString("ganesh")));                 
		
	}
}
