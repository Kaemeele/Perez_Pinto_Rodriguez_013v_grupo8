package BullyCars.Facturacion.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Services.FacturacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/facturacion")
@Tag(name = "Facturación", description = "Endpoints para la generación y gestión de facturas")
public class FacturacionController {
    
    @Autowired private FacturacionService service;

    @Operation(summary = "Crear una nueva factura")
    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody Factura f) { 
        return new ResponseEntity<>(service.generar(f), HttpStatus.CREATED); 
    }

    @Operation(summary = "Listar el historial de todas las facturas")
    @GetMapping
    public ResponseEntity<List<Factura>> listar() { 
        return ResponseEntity.ok(service.historial()); 
    }

    @Operation(summary = "Obtener detalles de una factura por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una factura por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Generar factura correspondiente a una cita específica")
    @PostMapping("/cita/{citaId}")
    public ResponseEntity<Factura> generarFacturaDeCita(@PathVariable Long citaId) {
        return new ResponseEntity<>(service.generarFacturaDeCita(citaId), HttpStatus.CREATED);
    }
}

