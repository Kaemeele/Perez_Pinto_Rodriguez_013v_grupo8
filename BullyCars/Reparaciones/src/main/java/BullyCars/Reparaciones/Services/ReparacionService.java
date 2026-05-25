package BullyCars.Reparaciones.Services;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class ReparacionService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private OrdenTrabajoRepository repository;

    // Obtiene la lista completa de todas las ordenes de trabajo/reparaciones registradas
    public List<OrdenTrabajo> listarOrdenes() { return repository.findAll(); }
    
    // Crea una nueva orden de trabajo de reparacion e inicializa su estado como "Pendiente"
    public OrdenTrabajo crearOrden(OrdenTrabajo orden) {
        orden.setEstado("Pendiente");
        return repository.save(orden);
    }
}