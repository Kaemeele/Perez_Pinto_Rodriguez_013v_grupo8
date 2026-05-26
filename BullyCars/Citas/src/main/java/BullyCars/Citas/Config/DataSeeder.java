package BullyCars.Citas.Config;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CitaRepository citaRepository;

    public DataSeeder(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (citaRepository.count() == 0) {
            Cita cita = new Cita();
            cita.setFechaHora(LocalDateTime.of(2026, 6, 1, 10, 0));
            cita.setDescripcionProblema("Revisión de frenos y cambio de aceite");
            cita.setClienteId(1L);
            cita.setVehiculoId(1L);
            citaRepository.save(cita);
        }
    }
}
