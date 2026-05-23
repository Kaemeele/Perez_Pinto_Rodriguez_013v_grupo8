package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;
import BullyCars.Citas.Proxy.ClienteProxy;
import BullyCars.Citas.Exceptions.*;

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    @Autowired private ClienteProxy clienteProxy;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        // 1. Validar integridad de datos reales
        if (cita.getClienteId() == null || cita.getVehiculoId() == null || cita.getFechaHora() == null || cita.getDescripcionProblema() == null || cita.getDescripcionProblema().isEmpty()) {
            throw new CitaInvalidaException("Datos incompletos.");
        }

        // 2. Validar fecha
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new FechaPasadaException("No puedes agendar en fechas pasadas.");
        }

        // 3. Validar disponibilidad (por vehículo y fechaHora)
        if (repository.findByVehiculoIdAndFechaHora(cita.getVehiculoId(), cita.getFechaHora()).isPresent()) {
            throw new CitaYaProgramadaException("El vehículo ya tiene una cita programada para esta fecha y hora.");
        }

        // 4. Validar existencia del cliente vía Proxy
        validarCliente(cita.getClienteId());

        return repository.save(cita);
    }

    private void validarCliente(Long id) {
        try {
            var respuesta = clienteProxy.obtenerClientePorId(id);
            if (respuesta == null) {
                throw new RuntimeException("Cliente no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("El microservicio de Clientes no responde.");
        }
    }
}