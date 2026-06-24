package BullyCars.Vehiculos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Services.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vehiculos")
@Tag(name = "Vehículos", description = "Endpoints para la gestión de vehículos y sus historiales")
public class VehiculoController {
    
    @Autowired
    private VehiculoService service;

    @Operation(summary = "Listar todos los vehículos registrados")
    @GetMapping
    public List<Vehiculo> listar() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Obtener un vehículo por su ID")
    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Registrar un nuevo vehículo")
    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return service.guardar(vehiculo);
    }

    @Operation(summary = "Eliminar un vehículo por su ID")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}