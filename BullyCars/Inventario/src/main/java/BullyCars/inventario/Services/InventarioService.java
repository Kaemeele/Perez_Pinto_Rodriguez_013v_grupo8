package BullyCars.inventario.Services;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class InventarioService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private RepuestoRepository repository;

    // Obtiene la lista completa de todos los repuestos registrados en el inventario
    public List<Repuesto> listarTodo() {
        return repository.findAll();
    }

    // Registra un nuevo repuesto o actualiza el stock/detalles de uno existente
    public Repuesto guardar(Repuesto repuesto) {
        return repository.save(repuesto);
    }
}