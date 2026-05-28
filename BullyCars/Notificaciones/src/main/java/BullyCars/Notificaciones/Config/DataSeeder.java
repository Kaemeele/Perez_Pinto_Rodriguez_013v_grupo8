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

            Notificacion notificacion2 = new Notificacion();
            notificacion2.setClienteId(2L);
            notificacion2.setMensaje("Su cita para la instalación del Wrap ha sido confirmada para el 15 de Junio.");
            notificacionRepository.save(notificacion2);
        }
    }
}

// GET (Listar Notificaciones)
// http://localhost:9081/api/v1/notificaciones

// GET (Obtener por ID)
// http://localhost:9081/api/v1/notificaciones/1

// POST (Enviar Notificación)
// http://localhost:9081/api/v1/notificaciones
// {
//   "clienteId": 1,
//   "mensaje": "Su orden de trabajo #3 ha sido procesada con éxito."
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/notificaciones/1


