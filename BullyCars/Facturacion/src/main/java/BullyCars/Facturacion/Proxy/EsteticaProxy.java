package BullyCars.Facturacion.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estetica-service")
public interface EsteticaProxy {

    @GetMapping("/api/v1/estetica/reservas/cita/{citaId}")
    ResponseEntity<Object> obtenerReservaPorCitaId(@PathVariable("citaId") Long citaId);

    @PutMapping("/api/v1/estetica/reservas/{id}/estado")
    ResponseEntity<Object> actualizarEstadoReserva(@PathVariable("id") Long id, @RequestParam("estado") String estado);
}
