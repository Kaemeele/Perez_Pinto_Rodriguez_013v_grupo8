package BullyCars.Citas.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Services.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/citas")
@Tag(name = "Citas", description = "Endpoints para la gestión de citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Obtener todas las citas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de citas obtenido con éxito")
    })
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        return ResponseEntity.ok(citaService.obtenerTodas());
    }

    @Operation(summary = "Crear nueva cita")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cita creada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cita.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "409", description = "Conflicto: Vehículo ya tiene cita en esta fecha u horario pasado")
    })
    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.agendar(cita));
    }

    @Operation(summary = "Obtener cita por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cita.class))),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return citaService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar cita")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Confirmar cita")
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Cita> confirmarCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.confirmarCita(id));
    }

    @Operation(summary = "Registrar facturación para la cita")
    @PutMapping("/{id}/facturar")
    public ResponseEntity<Cita> registrarFacturacion(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.registrarFacturacion(id));
    }
}