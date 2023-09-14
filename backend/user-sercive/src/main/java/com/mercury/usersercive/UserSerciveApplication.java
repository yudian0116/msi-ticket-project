package com.mercury.usersercive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Service",
				version = "1.0",
				description = "User Service API"
		)
)
public class UserSerciveApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSerciveApplication.class, args);
	}

}
