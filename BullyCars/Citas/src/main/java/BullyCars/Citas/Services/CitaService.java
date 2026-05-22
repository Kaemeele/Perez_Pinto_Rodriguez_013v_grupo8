package BullyCars.Citas.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;
import BullyCars.Citas.Clients.ClienteClient;
import BullyCars.Citas.Exceptions.*; 

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    @Autowired private ClienteClient clienteClient;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        if (cita.getNombreCliente() == null || cita.getNombreCliente().isEmpty() || cita.getDuracion() <= 0) {
            throw new CitaInvalidaException("Datos incompletos o duración inválida.");
        }

        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new FechaPasadaException("No puedes agendar en fechas pasadas.");
        }

        if (repository.existsByFechaAndHora(cita.getFecha(), cita.getHora())) {
            throw new CitaYaProgramadaException("El horario ya está ocupado.");
        }

        try {
            var response = clienteClient.obtenerClientePorId(cita.getClienteId());
            if (response == null) throw new RuntimeException("Cliente no existe.");
        } catch (Exception e) {
            throw new RuntimeException("El servicio de Clientes no responde.");
        }

        return repository.save(cita);
    }
}