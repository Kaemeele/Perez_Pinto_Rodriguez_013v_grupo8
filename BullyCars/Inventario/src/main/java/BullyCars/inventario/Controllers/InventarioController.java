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

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    
    @Autowired
    private InventarioService service;

    @GetMapping
    public ResponseEntity<List<Repuesto>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    @PostMapping
    public ResponseEntity<Repuesto> crear(@Valid @RequestBody Repuesto repuesto) {
        return new ResponseEntity<>(service.guardar(repuesto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/descontar")
    public ResponseEntity<Repuesto> descontarStock(@PathVariable Long id, @RequestParam Integer cantidad, @RequestParam(required = false) String descripcion) {
        return ResponseEntity.ok(service.descontarStock(id, cantidad, descripcion));
    }

    @PutMapping("/{id}/agregar")
    public ResponseEntity<Repuesto> agregarStock(@PathVariable Long id, @RequestParam Integer cantidad, @RequestParam(required = false) String descripcion) {
        return ResponseEntity.ok(service.agregarStock(id, cantidad, descripcion));
    }
}