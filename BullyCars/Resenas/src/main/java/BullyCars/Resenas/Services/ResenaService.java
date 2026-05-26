package BullyCars.Resenas.Services;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Repositories.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class ResenaService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private ResenaRepository repository;

    // Obtiene la lista completa de resenas, calificaciones y comentarios de los clientes
    public List<Resena> listarTodas() { return repository.findAll(); }

    // Registra una nueva resena o comentario de un cliente en la base de datos
    public Resena guardar(Resena resena) { return repository.save(resena); }
}