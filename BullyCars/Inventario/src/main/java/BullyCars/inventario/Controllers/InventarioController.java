package BullyCars.inventario.Controllers;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private InventarioService service;

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<Repuesto>> listar() {
        return ResponseEntity.ok(service.listarTodo());
    }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<Repuesto> crear(@Valid @RequestBody Repuesto repuesto) {
        return new ResponseEntity<>(service.guardar(repuesto), HttpStatus.CREATED);
    }
}