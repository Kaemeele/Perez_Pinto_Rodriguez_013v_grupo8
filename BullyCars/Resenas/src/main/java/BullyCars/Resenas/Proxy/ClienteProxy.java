package BullyCars.Resenas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-service")
public interface ClienteProxy {

    @GetMapping("/api/v1/clientes/{id}")
    ResponseEntity<Object> obtenerClientePorId(@PathVariable("id") Long id);
}
