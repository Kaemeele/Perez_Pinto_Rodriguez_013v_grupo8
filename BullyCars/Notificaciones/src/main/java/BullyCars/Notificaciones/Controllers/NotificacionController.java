package BullyCars.Notificaciones.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}