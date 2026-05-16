package BullyCars.Reparaciones.Services;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    private OrdenTrabajoRepository repository;

    public List<OrdenTrabajo> listarOrdenes() { return repository.findAll(); }
    
    public OrdenTrabajo crearOrden(OrdenTrabajo orden) {
        orden.setEstado("Pendiente");
        return repository.save(orden);
    }
}