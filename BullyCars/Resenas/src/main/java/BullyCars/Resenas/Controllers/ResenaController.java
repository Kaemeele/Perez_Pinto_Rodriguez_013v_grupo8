package BullyCars.Resenas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Services.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseñas", description = "Endpoints para la creación y visualización de opiniones y reseñas de clientes")
public class ResenaController {
    
    @Autowired
    private ResenaService service;

    @Operation(summary = "Listar todas las reseñas")
    @GetMapping
    public ResponseEntity<List<Resena>> listar() { return ResponseEntity.ok(service.listarTodas()); }

    @Operation(summary = "Crear una nueva reseña")
    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        return ResponseEntity.status(201).body(service.guardar(resena));
    }

    @Operation(summary = "Obtener detalles de una reseña por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una reseña por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}