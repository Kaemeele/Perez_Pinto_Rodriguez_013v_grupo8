package BullyCars.Estetica.Automotriz.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Services.EsteticaService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/estetica")
public class EsteticaController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private EsteticaService service;

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<ServicioEstetica>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<ServicioEstetica> crear(@RequestBody ServicioEstetica s) { 
        return new ResponseEntity<>(service.guardar(s), HttpStatus.CREATED); 
    }
}
