package BullyCars.Estetica.Automotriz.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Repositories.EsteticaRepository;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class EsteticaService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private EsteticaRepository repo;

    // Retorna la lista de todos los servicios esteticos automotrices disponibles
    public List<ServicioEstetica> listar() { return repo.findAll(); }

    // Guarda un nuevo servicio estetico o actualiza uno existente en la base de datos
    public ServicioEstetica guardar(ServicioEstetica s) { return repo.save(s); }
}