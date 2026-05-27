package BullyCars.Notificaciones.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Repositories.NotificacionRepository;

@Service
public class NotificacionService {
    
    @Autowired private NotificacionRepository repo;

    public Notificacion registrar(Notificacion n) { return repo.save(n); }

    public List<Notificacion> verTodas() { return repo.findAll(); }

    public Optional<Notificacion> obtenerPorId(Long id) { return repo.findById(id); }
}