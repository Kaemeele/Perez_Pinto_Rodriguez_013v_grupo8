package BullyCars.Estetica.Automotriz.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Services.EsteticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/estetica")
@Tag(name = "Estética Automotriz", description = "Endpoints para gestionar servicios y reservas de estética automotriz")
public class EsteticaController {
    
    @Autowired private EsteticaService service;

    @Operation(summary = "Listar todos los servicios de estética")
    @GetMapping
    public ResponseEntity<List<ServicioEstetica>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }

    @Operation(summary = "Crear un nuevo servicio de estética")
    @PostMapping
    public ResponseEntity<ServicioEstetica> crear(@RequestBody ServicioEstetica s) { 
        return new ResponseEntity<>(service.guardar(s), HttpStatus.CREATED); 
    }

    @Operation(summary = "Obtener un servicio de estética por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServicioEstetica> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un servicio de estética por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear una nueva reserva de estética")
    @PostMapping("/reservas")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> crearReserva(@RequestBody BullyCars.Estetica.Automotriz.Models.ReservaEstetica r) {
        return new ResponseEntity<>(service.crearReserva(r), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener detalles de una reserva por su ID")
    @GetMapping("/reservas/{id}")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> obtenerReservaPorId(@PathVariable Long id) {
        return service.obtenerReservaPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener una reserva de estética por el ID de la cita asociada")
    @GetMapping("/reservas/cita/{citaId}")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> obtenerReservaPorCitaId(@PathVariable Long citaId) {
        return service.obtenerReservaPorCitaId(citaId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar el estado de una reserva de estética")
    @PutMapping("/reservas/{id}/estado")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> actualizarEstadoReserva(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstadoReserva(id, estado));
    }
}

