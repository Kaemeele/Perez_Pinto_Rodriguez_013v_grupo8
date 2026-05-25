package BullyCars.Clientes.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Services.ClienteService;
import jakarta.validation.Valid;

/**
 * Controlador REST que expone los endpoints y maneja las peticiones HTTP de este microservicio.
 */
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private ClienteService service;

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(service.registrarCliente(cliente), HttpStatus.CREATED);
    }

    // Endpoint para registrar o guardar nueva informacion (Solicitud POST)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Map<String, String> credenciales) {
        String token = service.login(credenciales.get("email"), credenciales.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // Endpoint para recuperar informacion (Solicitud GET)
    @GetMapping("/buscar")
    public ResponseEntity<Cliente> obtenerPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.obtenerPorEmail(email));
    }
}   