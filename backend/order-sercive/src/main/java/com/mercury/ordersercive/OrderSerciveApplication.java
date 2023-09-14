package com.mercury.ordersercive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Order Service",
				version = "1.0",
				description = "Order Service API"
		)
)
public class OrderSerciveApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSerciveApplication.class, args);
	}

}
