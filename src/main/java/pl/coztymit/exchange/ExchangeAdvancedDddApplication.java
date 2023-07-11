package pl.coztymit.exchange;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EXCHANGE API", version = "2.0"))
@EnableScheduling
public class ExchangeAdvancedDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeAdvancedDddApplication.class, args);
	}

}
