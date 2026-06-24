package BullyCars.Reparaciones.Config;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class DataSeeder implements CommandLineRunner {
    private final OrdenTrabajoRepository ordenTrabajoRepository;

    public DataSeeder(OrdenTrabajoRepository ordenTrabajoRepository) {
        this.ordenTrabajoRepository = ordenTrabajoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ordenTrabajoRepository.count() == 0) {
            OrdenTrabajo ot = new OrdenTrabajo();
            ot.setDescripcion("Reemplazo de pastillas de freno delanteras");
            ot.setEstado("Pendiente");
            ot.setVehiculoId(1L);
            ot.setMecanicoAsignado("Carlos Mecánico");
            ordenTrabajoRepository.save(ot);

            OrdenTrabajo ot2 = new OrdenTrabajo();
            ot2.setDescripcion("Instalación de Suspensión Ajustable Coilover Tein");
            ot2.setEstado("En Proceso");
            ot2.setVehiculoId(2L);
            ot2.setMecanicoAsignado("Sebastián Tuner");
            ordenTrabajoRepository.save(ot2);
        }
    }
}

// GET (Listar Órdenes de Trabajo)
// http://localhost:9081/api/v1/reparaciones

// GET (Obtener por ID)
// http://localhost:9081/api/v1/reparaciones/1

// POST (Crear Orden de Trabajo)
// http://localhost:9081/api/v1/reparaciones
// {
//   "descripcion": "Ajuste de frenos traseros y purgado de líquido",
//   "estado": "Pendiente",
//   "vehiculoId": 1,
//   "mecanicoAsignado": "Carlos Mecánico"
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/reparaciones/1


