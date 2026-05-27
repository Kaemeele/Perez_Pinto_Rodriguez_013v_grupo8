package BullyCars.Proveedores.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Services.ProveedorService;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    
    @Autowired private ProveedorService service;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() { 
        return ResponseEntity.ok(service.listarTodo()); 
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor p) { 
        return new ResponseEntity<>(service.guardar(p), HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}