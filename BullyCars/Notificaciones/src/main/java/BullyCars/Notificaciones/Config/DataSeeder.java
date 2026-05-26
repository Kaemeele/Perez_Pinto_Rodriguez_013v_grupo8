package BullyCars.Notificaciones.Config;

import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Repositories.NotificacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final NotificacionRepository notificacionRepository;

    public DataSeeder(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (notificacionRepository.count() == 0) {
            Notificacion notificacion = new Notificacion();
            notificacion.setClienteId(1L);
            notificacion.setMensaje("Su vehículo está listo para revisión y retiro.");
            notificacionRepository.save(notificacion);
        }
    }
}
