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

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(service.registrarCliente(cliente), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Map<String, String> credenciales) {
        String token = service.login(credenciales.get("email"), credenciales.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Cliente> obtenerPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.obtenerPorEmail(email));
    }
}   