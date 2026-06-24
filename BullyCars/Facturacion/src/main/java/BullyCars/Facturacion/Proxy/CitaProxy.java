package BullyCars.Facturacion.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "citas-service")
public interface CitaProxy {

    @GetMapping("/api/v1/citas/{id}")
    ResponseEntity<Object> obtenerCitaPorId(@PathVariable("id") Long id);

    @PutMapping("/api/v1/citas/{id}/facturar")
    ResponseEntity<Object> registrarFacturacion(@PathVariable("id") Long id);
}
