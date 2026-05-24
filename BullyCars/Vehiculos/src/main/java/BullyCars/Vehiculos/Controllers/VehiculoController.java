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

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService service;

    @GetMapping
    public List<Vehiculo> listar() {
        return service.obtenerTodos();
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return service.guardar(vehiculo);
    }
}