package BullyCars.Vehiculos.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Repositories.VehiculoRepository;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class VehiculoService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private VehiculoRepository repository;

    // Obtiene el listado completo de todos los vehiculos registrados en el taller
    public List<Vehiculo> obtenerTodos() {
        return repository.findAll();
    }

    // Registra un nuevo vehiculo (o actualiza sus datos/dueno) en la base de datos
    public Vehiculo guardar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }
}