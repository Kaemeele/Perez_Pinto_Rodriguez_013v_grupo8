package BullyCars.inventario.Controllers;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
}