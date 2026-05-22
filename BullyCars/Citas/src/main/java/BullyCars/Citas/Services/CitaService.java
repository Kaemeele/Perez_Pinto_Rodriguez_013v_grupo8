package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;
import BullyCars.Citas.Clients.ClienteClient;
import BullyCars.Citas.Exceptions.FechaPasadaException; // <- IMPORTAR
import BullyCars.Citas.Exceptions.CitaYaProgramadaException; // <- IMPORTAR

@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    @Autowired
    private ClienteClient clienteClient;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        // 1. Validación Remota Feign con Clientes
        try {
            ResponseEntity<Object> response = clienteClient.obtenerClientePorId(cita.getClienteId());
            if (response == null || response.getStatusCode().isError()) {
                throw new RuntimeException("El cliente con ID " + cita.getClienteId() + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("El microservicio de Clientes no responde o el ID no es válido.");
        }

        // 2. Lanzar FechaPasadaException en lugar de RuntimeException
        if (cita.getFechaHora() == null || cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new FechaPasadaException("No se puede agendar una hora en días o bloques pasados.");
        }

        // 3. Lanzar CitaYaProgramadaException si hay choque de horarios
        Optional<Cita> existente = repository.findByVehiculoIdAndFechaHora(
            cita.getVehiculoId(), cita.getFechaHora());
        
        if (existente.isPresent()) {
            throw new CitaYaProgramadaException("El vehículo ingresado ya cuenta con una cita registrada para este bloque horario.");
        }

        return repository.save(cita);
    }
}