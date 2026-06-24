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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/reparaciones")
@Tag(name = "Reparaciones", description = "Endpoints para la gestión de órdenes de trabajo y repuestos usados")
public class ReparacionController {

    @Autowired
    private ReparacionService service;

    @Operation(summary = "Listar todas las órdenes de trabajo")
    @GetMapping
    public ResponseEntity<List<OrdenTrabajo>> listarTodas() {
        List<OrdenTrabajo> ordenes = service.listarOrdenes();
        return ResponseEntity.ok(ordenes); 
    }

    @Operation(summary = "Crear una nueva orden de trabajo")
    @PostMapping
    public ResponseEntity<OrdenTrabajo> crearOrden(@Valid @RequestBody OrdenTrabajo orden) {
        OrdenTrabajo nuevaOrden = service.crearOrden(orden);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED); 
    }

    @Operation(summary = "Obtener una orden de trabajo por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener una orden de trabajo por el ID de la cita asociada")
    @GetMapping("/cita/{citaId}")
    public ResponseEntity<OrdenTrabajo> obtenerPorCitaId(@PathVariable Long citaId) {
        return service.obtenerPorCitaId(citaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Agregar un repuesto usado a una orden de trabajo")
    @PostMapping("/{id}/repuestos")
    public ResponseEntity<RepuestoUsado> agregarRepuesto(
            @PathVariable Long id, 
            @RequestParam Long repuestoId, 
            @RequestParam Integer cantidad) {
        return new ResponseEntity<>(service.agregarRepuesto(id, repuestoId, cantidad), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar el estado y/o costo de mano de obra de una orden de trabajo")
    @PutMapping("/{id}/estado")
    public ResponseEntity<OrdenTrabajo> actualizarEstadoYClave(
            @PathVariable Long id, 
            @RequestParam(required = false) String estado, 
            @RequestParam(required = false) Double costoManoObra) {
        return ResponseEntity.ok(service.actualizarEstadoYClave(id, estado, costoManoObra));
    }

    @Operation(summary = "Eliminar una orden de trabajo por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}