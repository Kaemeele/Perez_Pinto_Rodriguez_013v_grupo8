package BullyCars.Citas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reparaciones-service")
public interface ReparacionProxy {

    @PostMapping("/api/v1/reparaciones")
    ResponseEntity<Object> crearOrden(@RequestBody Object ordenTrabajoReq);
}
