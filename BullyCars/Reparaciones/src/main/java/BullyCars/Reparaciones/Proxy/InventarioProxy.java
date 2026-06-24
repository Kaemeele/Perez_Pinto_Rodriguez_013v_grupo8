package BullyCars.Reparaciones.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventario-service")
public interface InventarioProxy {

    @GetMapping("/api/v1/inventario/{id}")
    ResponseEntity<Object> obtenerRepuestoPorId(@PathVariable("id") Long id);

    @PutMapping("/api/v1/inventario/{id}/descontar")
    ResponseEntity<Object> descontarStock(
            @PathVariable("id") Long id, 
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam(value = "descripcion", required = false) String descripcion
    );
}
