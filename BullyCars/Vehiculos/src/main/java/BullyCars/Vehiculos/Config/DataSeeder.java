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
        }
    }
}
