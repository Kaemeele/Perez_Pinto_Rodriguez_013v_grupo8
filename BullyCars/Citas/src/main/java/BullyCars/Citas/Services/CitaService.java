package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BullyCars.Citas.Exceptions.CitaInvalidaException;
import BullyCars.Citas.Exceptions.CitaYaProgramadaException;
import BullyCars.Citas.Exceptions.FechaPasadaException;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Proxy.ClienteProxy;
import BullyCars.Citas.Repositories.CitaRepository;

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    
    @Autowired private ClienteProxy clienteProxy;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        
        if (cita.getClienteId() == null || cita.getVehiculoId() == null || cita.getFechaHora() == null || cita.getDescripcionProblema() == null || cita.getDescripcionProblema().isEmpty()) {
            throw new CitaInvalidaException("Datos incompletos.");
        }

        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new FechaPasadaException("No puedes agendar en fechas pasadas.");
        }

        if (repository.findByVehiculoIdAndFechaHora(cita.getVehiculoId(), cita.getFechaHora()).isPresent()) {
            throw new CitaYaProgramadaException("El vehiculo ya tiene una cita programada para esta fecha y hora.");
        }

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

    public Optional<Cita> obtenerPorId(Long id) { return repository.findById(id); }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }
}