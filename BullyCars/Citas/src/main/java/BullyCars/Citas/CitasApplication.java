package BullyCars.Citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // <- IMPORTAR

/**
 * Clase de entrada principal para arrancar el microservicio utilizando Spring Boot.
 */
@SpringBootApplication
@EnableFeignClients
public class CitasApplication {
	public static void main(String[] args) {
		SpringApplication.run(CitasApplication.class, args);
	}
}