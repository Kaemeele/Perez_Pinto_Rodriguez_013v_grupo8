package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Repositories.CitaRepository;
import BullyCars.Citas.Proxy.ClienteProxy;
import BullyCars.Citas.Exceptions.*;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class CitaService {

    // Inyeccion automatica de dependencias de Spring
    @Autowired private CitaRepository repository;
    // Inyeccion automatica de dependencias de Spring
    @Autowired private ClienteProxy clienteProxy;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    public Cita agendar(Cita cita) {
        // 1. Valida que los datos obligatorios no esten vacios
        if (cita.getClienteId() == null || cita.getVehiculoId() == null || cita.getFechaHora() == null || cita.getDescripcionProblema() == null || cita.getDescripcionProblema().isEmpty()) {
            throw new CitaInvalidaException("Datos incompletos.");
        }

        // 2. Valida que la fecha y hora de la cita no sea en el pasado
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new FechaPasadaException("No puedes agendar en fechas pasadas.");
        }

        // 3. Valida que el vehiculo no tenga otra cita agendada para la misma fecha y hora
        if (repository.findByVehiculoIdAndFechaHora(cita.getVehiculoId(), cita.getFechaHora()).isPresent()) {
            throw new CitaYaProgramadaException("El vehiculo ya tiene una cita programada para esta fecha y hora.");
        }

        // 4. Llama al microservicio de Clientes para validar que el cliente ingresado exista
        validarCliente(cita.getClienteId());

        // 5. Si todas las validaciones son correctas, guarda la cita en la base de datos
        return repository.save(cita);
    }

    private void validarCliente(Long id) {
        try {
            // Intenta obtener los datos del cliente consultando al microservicio de Clientes mediante Feign
            var respuesta = clienteProxy.obtenerClientePorId(id);
            
            // Si el microservicio responde pero no encuentra el cliente (retorna nulo), lanza un error
            if (respuesta == null) {
                throw new RuntimeException("Cliente no existe.");
            }
        } catch (Exception e) {
            // Si ocurre algun fallo de conexion o error al comunicarse con el microservicio, se captura aqui
            throw new RuntimeException("El microservicio de Clientes no responde.");
        }
    }
}