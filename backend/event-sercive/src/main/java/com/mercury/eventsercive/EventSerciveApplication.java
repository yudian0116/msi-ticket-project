package com.mercury.eventsercive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Event Service",
				version = "1.0",
				description = "Event Service API"
		)
)
public class EventSerciveApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSerciveApplication.class, args);
	}

}
