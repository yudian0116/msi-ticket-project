package com.mercury.apigateway;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public GroupedOpenApi orderApis() {
		return GroupedOpenApi.builder().pathsToMatch("/orders/**").group("order").build();
	}

	@Bean
	public GroupedOpenApi eventApis() {
		return GroupedOpenApi.builder().pathsToMatch("/events/**").group("event").build();
	}

	@Bean
	public GroupedOpenApi cartApis() {
		return GroupedOpenApi.builder().pathsToMatch("/carts/**").group("cart").build();
	}

	@Bean
	public GroupedOpenApi userApis() {
		return GroupedOpenApi.builder().pathsToMatch("/users/**").group("user").build();
	}

	@Bean
	public GroupedOpenApi inventoryApis() {
		return GroupedOpenApi.builder().pathsToMatch("/inventories/**").group("inventory").build();
	}
}
