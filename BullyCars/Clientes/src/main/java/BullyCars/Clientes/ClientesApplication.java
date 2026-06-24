package BullyCars.Clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class ClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
}
