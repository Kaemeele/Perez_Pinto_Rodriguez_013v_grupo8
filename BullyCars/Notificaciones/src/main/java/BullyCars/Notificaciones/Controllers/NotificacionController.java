package BullyCars.Notificaciones.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Services.NotificacionService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private NotificacionService service;

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion n) { 
        return new ResponseEntity<>(service.registrar(n), HttpStatus.CREATED); 
    }

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() { 
        return ResponseEntity.ok(service.verTodas()); 
    }
}