package BullyCars.Citas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estetica-service")
public interface EsteticaProxy {

    @GetMapping("/api/v1/estetica/{id}")
    ResponseEntity<Object> obtenerServicioPorId(@PathVariable("id") Long id);

    @PostMapping("/api/v1/estetica/reservas")
    ResponseEntity<Object> crearReserva(@RequestBody Object reservaEsteticaReq);
}
