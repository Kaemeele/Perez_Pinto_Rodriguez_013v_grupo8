package BullyCars.Resenas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Clase de entrada principal para arrancar el microservicio utilizando Spring Boot.
 */
@SpringBootApplication
@EnableDiscoveryClient

public class ResenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResenasApplication.class, args);
	}

}
