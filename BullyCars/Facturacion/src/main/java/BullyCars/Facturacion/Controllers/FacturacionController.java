package BullyCars.Facturacion.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Services.FacturacionService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/facturacion")
public class FacturacionController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private FacturacionService service;

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody Factura f) { 
        return new ResponseEntity<>(service.generar(f), HttpStatus.CREATED); 
    }

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public ResponseEntity<List<Factura>> listar() { 
        return ResponseEntity.ok(service.historial()); 
    }
}
