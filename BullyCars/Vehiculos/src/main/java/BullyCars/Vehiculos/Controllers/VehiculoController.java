package BullyCars.Vehiculos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Services.VehiculoService;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private VehiculoService service;

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping
    public List<Vehiculo> listar() {
        return service.obtenerTodos();
    }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return service.guardar(vehiculo);
    }
}