package BullyCars.Notificaciones.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Repositories.NotificacionRepository;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class NotificacionService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private NotificacionRepository repo;

    // Registra y envia una nueva notificacion en el sistema
    public Notificacion registrar(Notificacion n) { return repo.save(n); }

    // Obtiene la lista completa de todas las notificaciones registradas
    public List<Notificacion> verTodas() { return repo.findAll(); }
}