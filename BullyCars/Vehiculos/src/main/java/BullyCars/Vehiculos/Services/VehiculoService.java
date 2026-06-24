package BullyCars.Vehiculos.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Repositories.VehiculoRepository;

@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository repository;

    public List<Vehiculo> obtenerTodos() {
        return repository.findAll();
    }

    public Vehiculo obtenerPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new BullyCars.Vehiculos.Exceptions.VehiculoNoEncontradoException("Vehículo no encontrado con ID: " + id));
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new BullyCars.Vehiculos.Exceptions.VehiculoNoEncontradoException("Vehículo no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}