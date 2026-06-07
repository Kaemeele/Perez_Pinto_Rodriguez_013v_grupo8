package BullyCars.Citas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehiculos-service")
public interface VehiculoProxy {

    @GetMapping("/api/v1/vehiculos/{id}")
    ResponseEntity<Object> obtenerVehiculoPorId(@PathVariable("id") Long id);
}
