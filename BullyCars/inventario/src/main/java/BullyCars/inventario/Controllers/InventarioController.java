package BullyCars.Inventario.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Inventario.Models.Repuesto;
import BullyCars.Inventario.Services.InventarioService;
import jakarta.validation.Valid;

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