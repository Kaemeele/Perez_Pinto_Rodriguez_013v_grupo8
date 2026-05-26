package BullyCars.Estetica.Automotriz.Config;

import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Repositories.EsteticaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final EsteticaRepository esteticaRepository;

    public DataSeeder(EsteticaRepository esteticaRepository) {
        this.esteticaRepository = esteticaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (esteticaRepository.count() == 0) {
            ServicioEstetica servicio = new ServicioEstetica();
            servicio.setNombre("Lavado Premium");
            servicio.setDescripcion("Lavado exterior, interior y encerado completo");
            servicio.setPrecio(15000.00);
            esteticaRepository.save(servicio);
        }
    }
}
