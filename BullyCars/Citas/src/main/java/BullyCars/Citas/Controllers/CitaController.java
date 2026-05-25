package BullyCars.Citas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Services.CitaService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private CitaService service;

    @GetMapping//Obtener la lista de Citas (Endpoint GET)
    public List<Cita> listar() {
        return service.obtenerTodas();
    }

    @PostMapping// Agendar una nueva Cita (Endpoint POST)
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        Cita nuevaCita = service.agendar(cita);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }
}