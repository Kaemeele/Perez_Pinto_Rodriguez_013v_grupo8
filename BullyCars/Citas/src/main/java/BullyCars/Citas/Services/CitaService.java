package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;

@Service
@FeignClient(name = "clientes-service", path = "/api/v1/clientes")
public interface ClienteClient {

    // Este método debe mapear exactamente el GetMapping por ID que tienes en tu ClienteController
    @GetMapping("/{id}")
    ResponseEntity<Object> obtenerClientePorId(@PathVariable("id") Long id);
}
public class CitaService {
    @Autowired
    private CitaRepository repository;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        // 1. Validar que la fecha no sea pasada
        if (cita.getFechaHora() == null || cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se puede agendar en una fecha pasada.");
        }

        // 2. Validar disponibilidad
        Optional<Cita> existente = repository.findByVehiculoIdAndFechaHora(
            cita.getVehiculoId(), cita.getFechaHora());
        
        if (existente.isPresent()) {
            throw new RuntimeException("El vehículo ya tiene una cita a esa hora.");
        }

        return repository.save(cita);
    }

}