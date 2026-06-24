package BullyCars.Resenas.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "citas-service")
public interface CitaProxy {

    @GetMapping("/api/v1/citas")
    ResponseEntity<List<Object>> listarTodas();
}
