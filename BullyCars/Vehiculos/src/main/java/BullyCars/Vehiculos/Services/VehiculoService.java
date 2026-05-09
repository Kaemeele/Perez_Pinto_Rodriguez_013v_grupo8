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

    public Vehiculo guardar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }
}