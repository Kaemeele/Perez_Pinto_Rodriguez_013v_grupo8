package BullyCars.inventario.Config;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.RepuestoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
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

            Repuesto repuesto2 = new Repuesto();
            repuesto2.setNombre("Filtro de Aire K&N Alto Flujo");
            repuesto2.setStock(15);
            repuesto2.setPrecio(45000.00);
            repuestoRepository.save(repuesto2);
        }
    }
}

// GET (Listar Repuestos)
// http://localhost:9081/api/v1/inventario

// GET (Obtener por ID)
// http://localhost:9081/api/v1/inventario/1

// POST (Crear Repuesto)
// http://localhost:9081/api/v1/inventario
// {
//   "nombre": "Aceite Sintético Mobil 1 5W30",
//   "stock": 30,
//   "precio": 35000.00
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/inventario/1


