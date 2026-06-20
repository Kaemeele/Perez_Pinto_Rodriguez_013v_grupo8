package BullyCars.Notificaciones.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Services.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Endpoints para el envío y registro de notificaciones")
public class NotificacionController {
    
    @Autowired private NotificacionService service;

    @Operation(summary = "Registrar y enviar una notificación")
    @PostMapping
    public ResponseEntity<Notificacion> enviar(@RequestBody Notificacion n) { 
        return new ResponseEntity<>(service.registrar(n), HttpStatus.CREATED); 
    }

    @Operation(summary = "Listar todas las notificaciones registradas")
    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() { 
        return ResponseEntity.ok(service.verTodas()); 
    }

    @Operation(summary = "Obtener detalles de una notificación por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una notificación por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}