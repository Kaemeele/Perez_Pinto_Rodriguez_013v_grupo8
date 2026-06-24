package BullyCars.Clientes.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Endpoints para la gestión de clientes y autenticación")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Registrar un nuevo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(service.registrarCliente(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Iniciar sesión para obtener un token de autenticación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticación exitosa, token generado",
            content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"token\": \"eyJhbGciOiJIUzI1NiIsIn...\"}"))),
        @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Map<String, String> credenciales) {
        String token = service.login(credenciales.get("email"), credenciales.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @Operation(summary = "Obtener detalles de un cliente por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @Operation(summary = "Buscar un cliente por su correo electrónico")
    @GetMapping("/buscar")
    public ResponseEntity<Cliente> obtenerPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.obtenerPorEmail(email));
    }

    @Operation(summary = "Eliminar un cliente por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}   