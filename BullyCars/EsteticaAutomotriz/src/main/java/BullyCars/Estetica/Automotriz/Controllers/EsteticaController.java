package BullyCars.Estetica.Automotriz.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Services.EsteticaService;

@RestController
@RequestMapping("/api/v1/estetica")
public class EsteticaController {
    
    @Autowired private EsteticaService service;

    @GetMapping
    public ResponseEntity<List<ServicioEstetica>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }

    @PostMapping
    public ResponseEntity<ServicioEstetica> crear(@RequestBody ServicioEstetica s) { 
        return new ResponseEntity<>(service.guardar(s), HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioEstetica> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservas")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> crearReserva(@RequestBody BullyCars.Estetica.Automotriz.Models.ReservaEstetica r) {
        return new ResponseEntity<>(service.crearReserva(r), HttpStatus.CREATED);
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> obtenerReservaPorId(@PathVariable Long id) {
        return service.obtenerReservaPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/reservas/cita/{citaId}")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> obtenerReservaPorCitaId(@PathVariable Long citaId) {
        return service.obtenerReservaPorCitaId(citaId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reservas/{id}/estado")
    public ResponseEntity<BullyCars.Estetica.Automotriz.Models.ReservaEstetica> actualizarEstadoReserva(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstadoReserva(id, estado));
    }
}

