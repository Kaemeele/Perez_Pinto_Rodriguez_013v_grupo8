package BullyCars.Reparaciones.Config;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
        }
    }
}
