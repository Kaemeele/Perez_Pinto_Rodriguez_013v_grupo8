package BullyCars.Facturacion.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Services.FacturacionService;

@RestController
@RequestMapping("/api/v1/facturacion")
public class FacturacionController {
    
    @Autowired private FacturacionService service;

    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody Factura f) { 
        return new ResponseEntity<>(service.generar(f), HttpStatus.CREATED); 
    }

    @GetMapping
    public ResponseEntity<List<Factura>> listar() { 
        return ResponseEntity.ok(service.historial()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cita/{citaId}")
    public ResponseEntity<Factura> generarFacturaDeCita(@PathVariable Long citaId) {
        return new ResponseEntity<>(service.generarFacturaDeCita(citaId), HttpStatus.CREATED);
    }
}

