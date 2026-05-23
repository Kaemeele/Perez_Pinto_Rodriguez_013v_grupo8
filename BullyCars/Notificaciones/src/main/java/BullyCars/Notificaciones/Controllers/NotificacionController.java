package BullyCars.Notificaciones.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Services.NotificacionService;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    @Autowired private NotificacionService service;

    @PostMapping
    public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion n) { 
        return new ResponseEntity<>(service.registrar(n), HttpStatus.CREATED); 
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() { 
        return ResponseEntity.ok(service.verTodas()); 
    }
}