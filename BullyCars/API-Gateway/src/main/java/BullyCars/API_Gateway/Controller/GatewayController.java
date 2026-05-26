package BullyCars.API_Gateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "API-Gateway");
        response.put("status", "UP");
        response.put("message", "Bienvenido al API Gateway de BullyCars. Usa las rutas /api/v1/* para acceder a los microservicios.");
        return response;
    }
}
