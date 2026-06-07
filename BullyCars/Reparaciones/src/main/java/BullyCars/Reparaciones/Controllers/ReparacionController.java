package BullyCars.Reparaciones.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Models.RepuestoUsado;
import BullyCars.Reparaciones.Services.ReparacionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reparaciones")
public class ReparacionController {

    @Autowired
    private ReparacionService service;

    @GetMapping
    public ResponseEntity<List<OrdenTrabajo>> listarTodas() {
        List<OrdenTrabajo> ordenes = service.listarOrdenes();
        return ResponseEntity.ok(ordenes); 
    }

    @PostMapping
    public ResponseEntity<OrdenTrabajo> crearOrden(@Valid @RequestBody OrdenTrabajo orden) {
        OrdenTrabajo nuevaOrden = service.crearOrden(orden);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<OrdenTrabajo> obtenerPorCitaId(@PathVariable Long citaId) {
        return service.obtenerPorCitaId(citaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/repuestos")
    public ResponseEntity<RepuestoUsado> agregarRepuesto(
            @PathVariable Long id, 
            @RequestParam Long repuestoId, 
            @RequestParam Integer cantidad) {
        return new ResponseEntity<>(service.agregarRepuesto(id, repuestoId, cantidad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<OrdenTrabajo> actualizarEstadoYClave(
            @PathVariable Long id, 
            @RequestParam(required = false) String estado, 
            @RequestParam(required = false) Double costoManoObra) {
        return ResponseEntity.ok(service.actualizarEstadoYClave(id, estado, costoManoObra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}