package BullyCars.Citas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Services.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = service.agendar(cita);
            return ResponseEntity.ok(nuevaCita);
        } catch (RuntimeException e) {
            // Esto devuelve el error "El vehículo ya tiene una cita..." con código 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}