package BullyCars.inventario.Controllers;
import org.springframework.http.ResponseEntity;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventario")
@Tag(name = "Inventario", description = "Endpoints para la gestión de repuestos y movimientos de inventario")
public class InventarioController {
    
    @Autowired
    private InventarioService service;

    @Operation(summary = "Listar todos los repuestos en inventario")
    @GetMapping
    public ResponseEntity<List<Repuesto>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @Operation(summary = "Crear un nuevo repuesto")
    @PostMapping
    public ResponseEntity<Repuesto> crear(@Valid @RequestBody Repuesto repuesto) {
        return new ResponseEntity<>(service.guardar(repuesto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un repuesto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un repuesto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Descontar stock de un repuesto")
    @PutMapping("/{id}/descontar")
    public ResponseEntity<Repuesto> descontarStock(@PathVariable Long id, @RequestParam Integer cantidad, @RequestParam(required = false) String descripcion) {
        return ResponseEntity.ok(service.descontarStock(id, cantidad, descripcion));
    }

    @Operation(summary = "Agregar stock a un repuesto")
    @PutMapping("/{id}/agregar")
    public ResponseEntity<Repuesto> agregarStock(@PathVariable Long id, @RequestParam Integer cantidad, @RequestParam(required = false) String descripcion) {
        return ResponseEntity.ok(service.agregarStock(id, cantidad, descripcion));
    }
}