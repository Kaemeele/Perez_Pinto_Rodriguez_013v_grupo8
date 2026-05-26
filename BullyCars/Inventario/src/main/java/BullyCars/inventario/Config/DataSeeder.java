package BullyCars.inventario.Config;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.RepuestoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final RepuestoRepository repuestoRepository;

    public DataSeeder(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repuestoRepository.count() == 0) {
            Repuesto repuesto = new Repuesto();
            repuesto.setNombre("Pastillas de Freno");
            repuesto.setStock(50);
            repuesto.setPrecio(25000.00);
            repuestoRepository.save(repuesto);
        }
    }
}
