package BullyCars.Proveedores.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Services.ProveedorService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private ProveedorService service;

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() { 
        return ResponseEntity.ok(service.listarTodo()); 
    }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor p) { 
        return new ResponseEntity<>(service.guardar(p), HttpStatus.CREATED); 
    }
}