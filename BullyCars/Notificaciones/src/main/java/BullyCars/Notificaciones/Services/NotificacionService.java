package BullyCars.Notificaciones.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Models.LogEnvio;
import BullyCars.Notificaciones.Repositories.NotificacionRepository;

@Service
public class NotificacionService {
    
    @Autowired private NotificacionRepository repo;

    @org.springframework.transaction.annotation.Transactional
    public Notificacion registrar(Notificacion n) {
        if (n.getLogs() == null) {
            n.setLogs(new java.util.ArrayList<>());
        }
        
        LogEnvio defaultLog = new LogEnvio();
        defaultLog.setCanal("EMAIL");
        defaultLog.setEstado("ENVIADO");
        defaultLog.setFechaHora(java.time.LocalDateTime.now());
        defaultLog.setNotificacion(n);
        n.getLogs().add(defaultLog);

        return repo.save(n);
    }

    public List<Notificacion> verTodas() { return repo.findAll(); }

    public Optional<Notificacion> obtenerPorId(Long id) { return repo.findById(id); }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada con ID: " + id);
        }
        repo.deleteById(id);
    }
}