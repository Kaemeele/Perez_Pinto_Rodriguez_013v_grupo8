package BullyCars.Facturacion.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificaciones-service")
public interface NotificacionProxy {

    @PostMapping("/api/v1/notificaciones")
    ResponseEntity<Object> enviarNotificacion(@RequestBody Object notificacionReq);
}
