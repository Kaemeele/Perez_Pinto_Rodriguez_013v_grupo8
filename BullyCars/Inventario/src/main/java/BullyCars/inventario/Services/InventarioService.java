package BullyCars.inventario.Services;

import java.util.Optional;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioService {
    
    @Autowired
    private RepuestoRepository repository;

    public List<Repuesto> listarTodo() {
        return repository.findAll();
    }

    public Repuesto guardar(Repuesto repuesto) {
        return repository.save(repuesto);
    }

    public Optional<Repuesto> obtenerPorId(Long id) { return repository.findById(id); }
}