package BullyCars.Inventario.Services;

import BullyCars.Inventario.Models.Repuesto;
import BullyCars.Inventario.Repositories.RepuestoRepository;
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
}