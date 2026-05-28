package BullyCars.Vehiculos.Config;

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Repositories.VehiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final VehiculoRepository vehiculoRepository;

    public DataSeeder(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (vehiculoRepository.count() == 0) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPatente("AB12CD");
            vehiculo.setMarca("Toyota");
            vehiculo.setModelo("Yaris");
            vehiculo.setAnio(2022);
            vehiculoRepository.save(vehiculo);

            Vehiculo vehiculo2 = new Vehiculo();
            vehiculo2.setPatente("XY78ZW");
            vehiculo2.setMarca("Ford");
            vehiculo2.setModelo("Mustang");
            vehiculo2.setAnio(2024);
            vehiculoRepository.save(vehiculo2);
        }
    }
}

// GET (Listar todos)
// http://localhost:9081/api/v1/vehiculos

// GET (Obtener por ID)
// http://localhost:9081/api/v1/vehiculos/1

// POST (Crear Vehículo)
// http://localhost:9081/api/v1/vehiculos
// {
//   "patente": "GH90IJ",
//   "marca": "Chevrolet",
//   "modelo": "Sail",
//   "anio": 2021
// }

