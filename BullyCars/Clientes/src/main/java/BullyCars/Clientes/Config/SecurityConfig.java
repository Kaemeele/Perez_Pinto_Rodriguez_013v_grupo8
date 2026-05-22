package BullyCars.Clientes.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Algoritmo de hashing obligatorio por rúbrica
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para poder probar las APIs en Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/clientes/registrar", "/api/v1/clientes/login").permitAll() // Rutas públicas
                .anyRequest().permitAll() // Permitimos el resto temporalmente para interactuar con tus otros microservicios
            );
        return http.build();
    }
}