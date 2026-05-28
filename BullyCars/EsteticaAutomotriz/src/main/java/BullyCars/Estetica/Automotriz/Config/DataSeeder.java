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

            ServicioEstetica servicio2 = new ServicioEstetica();
            servicio2.setNombre("Ceramic Coating 9H Nano-Estelar");
            servicio2.setDescripcion("Protección cerámica de pintura con curado infrarrojo de 9H dureza");
            servicio2.setPrecio(280000.00);
            esteticaRepository.save(servicio2);
        }
    }
}

// GET (Listar Servicios)
// http://localhost:9081/api/v1/estetica

// GET (Obtener por ID)
// http://localhost:9081/api/v1/estetica/1

// POST (Crear Servicio de Estética)
// http://localhost:9081/api/v1/estetica
// {
//   "nombre": "Pulido de Ópticas",
//   "descripcion": "Eliminación de opacidad y aplicación de sellador UV",
//   "precio": 25000.00
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/estetica/1


