package BullyCars.Resenas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Services.ResenaService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private ResenaService service;

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<Resena>> listar() { return ResponseEntity.ok(service.listarTodas()); }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        return ResponseEntity.status(201).body(service.guardar(resena));
    }
}