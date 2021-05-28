package com.apimicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
	
	@Autowired
    AuthenticationFilter filter;

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {		
		return builder.routes()
				
				/**
				 * Route to Register microservice auth-service
				 */
				.route(p -> p.path("/api/auth/register") 
						.uri("https://ms-auth-service-v02.herokuapp.com"))
				
				/**
				 * Route to list all Users
				 */
				.route(p -> p.path("/api/users") 
						.filters(f -> f.filter(filter))
						.uri("https://ms-user-service-v02.herokuapp.com"))
				
				.build();
	}
}
