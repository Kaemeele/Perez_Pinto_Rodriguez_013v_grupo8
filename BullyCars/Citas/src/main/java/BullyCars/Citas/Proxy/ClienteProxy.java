package BullyCars.Citas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign declarativo para realizar llamadas remotas a otros microservicios.
 */
@FeignClient(name = "clientes-service")
public interface ClienteProxy {

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping("/api/v1/clientes/{id}")
    ResponseEntity<Object> obtenerClientePorId(@PathVariable("id") Long id);
}