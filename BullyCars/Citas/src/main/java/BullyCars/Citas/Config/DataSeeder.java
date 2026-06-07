package BullyCars.Citas.Config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;

// @Component
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

            Cita cita2 = new Cita();
            cita2.setFechaHora(LocalDateTime.of(2026, 6, 15, 14, 30));
            cita2.setDescripcionProblema("Instalación de Stealth Matte Wrap Premium");
            cita2.setClienteId(2L);
            cita2.setVehiculoId(2L);
            citaRepository.save(cita2);
        }
    }
}

// GET (Listar todas)
// http://localhost:9081/api/v1/citas

// GET (Obtener por ID)
// http://localhost:9081/api/v1/citas/1

// POST (Crear Cita)
// http://localhost:9081/api/v1/citas
// {
//   "fechaHora": "2026-07-10T11:00:00",
//   "descripcionProblema": "Alineación, balanceo y cambio de amortiguadores delanteros",
//   "clienteId": 1,
//   "vehiculoId": 1
// }

// DELETE (Eliminar por ID)
// http://localhost:9081/api/v1/citas/1


