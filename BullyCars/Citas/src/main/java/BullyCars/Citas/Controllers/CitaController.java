package BullyCars.Citas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Services.CitaService;



@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        Cita nuevaCita = service.agendar(cita);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Cita> confirmarCita(@PathVariable Long id) {
        return ResponseEntity.ok(service.confirmarCita(id));
    }

    @PutMapping("/{id}/facturar")
    public ResponseEntity<Cita> registrarFacturacion(@PathVariable Long id) {
        return ResponseEntity.ok(service.registrarFacturacion(id));
    }
    
}