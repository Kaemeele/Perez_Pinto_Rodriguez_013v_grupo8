package BullyCars.Proveedores.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Services.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "Endpoints para la gestión de proveedores e insumos")
public class ProveedorController {
    
    @Autowired private ProveedorService service;

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() { 
        return ResponseEntity.ok(service.listarTodo()); 
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor p) { 
        return new ResponseEntity<>(service.guardar(p), HttpStatus.CREATED); 
    }

    @Operation(summary = "Obtener detalles de un proveedor por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un proveedor por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}