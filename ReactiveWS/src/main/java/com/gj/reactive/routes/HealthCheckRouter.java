package com.gj.reactive.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gj.reactive.handlers.IHeartBeatChecker;

@Configuration
@EnableWebFlux
public class HealthCheckRouter implements WebFluxConfigurer{

	@Autowired
	@Qualifier("heartBeatService")
	IHeartBeatChecker heartBeatService;
    
	@Bean
    public RouterFunction<ServerResponse> routerFunctionHealthCheck() {
     
    return	RouterFunctions.route()
    		               .before(t->{System.out.println("filter before-1");return t;})
    		               .after((m,n)->{System.out.println("filter after-1");return n;})
    		               .add(RouterFunctions.route(RequestPredicates.GET("/heartbeat")
    		               .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), heartBeatService::getServicsStatus))
    		               .filter((request,next)->{System.out.println("last filter-1");return next.handle(request);}).add(routerFunctionGreeting())
    		               .build();
    }
	
	
	public RouterFunction<ServerResponse> routerFunctionGreeting() {

		return RouterFunctions.route(RequestPredicates.GET("/greet")
				              .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				              heartBeatService::getGreetingMessage);

	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/")
		        .allowedOrigins("*")
		        .allowedHeaders("COMPANY_HEADER")
		        .allowedMethods("POST, PUT, GET, OPTIONS, DELETE")
		        .allowCredentials(true).maxAge(3600);
	}
    



}
