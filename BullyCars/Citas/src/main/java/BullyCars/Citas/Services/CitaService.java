package BullyCars.Citas.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BullyCars.Citas.Exceptions.CitaInvalidaException;
import BullyCars.Citas.Exceptions.CitaYaProgramadaException;
import BullyCars.Citas.Exceptions.FechaPasadaException;
import BullyCars.Citas.Models.Cita;
import BullyCars.Citas.Models.EstadoCitaHistorial;
import BullyCars.Citas.Proxy.ClienteProxy;
import BullyCars.Citas.Proxy.EsteticaProxy;
import BullyCars.Citas.Proxy.ReparacionProxy;
import BullyCars.Citas.Proxy.VehiculoProxy;
import BullyCars.Citas.Repositories.CitaRepository;
import BullyCars.Citas.Repositories.EstadoCitaHistorialRepository;

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    @Autowired private EstadoCitaHistorialRepository historialRepository;
    
    @Autowired private ClienteProxy clienteProxy;
    @Autowired private VehiculoProxy vehiculoProxy;
    @Autowired private ReparacionProxy reparacionProxy;
    @Autowired private EsteticaProxy esteticaProxy;

    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    @Transactional
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

        if (cita.getTipoServicio() == null) {
            cita.setTipoServicio("REPARACION");
        }

        validarCliente(cita.getClienteId());
        validarVehiculo(cita.getVehiculoId());

        if ("ESTETICA".equalsIgnoreCase(cita.getTipoServicio())) {
            if (cita.getServicioId() == null) {
                throw new CitaInvalidaException("Para citas de ESTETICA es obligatorio ingresar el servicioId.");
            }
            validarServicioEstetica(cita.getServicioId());
        }

        Cita savedCita = repository.save(cita);

        EstadoCitaHistorial log = new EstadoCitaHistorial();
        log.setFechaHora(LocalDateTime.now());
        log.setEstado("PROGRAMADA");
        log.setDetalle("Cita de tipo " + savedCita.getTipoServicio() + " programada con éxito.");
        log.setCita(savedCita);
        historialRepository.save(log);

        return savedCita;
    }

    private void validarCliente(Long id) {
        try {
            var respuesta = clienteProxy.obtenerClientePorId(id);
            if (respuesta == null || respuesta.getStatusCode().isError()) {
                throw new RuntimeException("Cliente con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el cliente. Microservicio Clientes no responde o ID inválido: " + e.getMessage());
        }
    }

    private void validarVehiculo(Long id) {
        try {
            var respuesta = vehiculoProxy.obtenerVehiculoPorId(id);
            if (respuesta == null || respuesta.getStatusCode().isError()) {
                throw new RuntimeException("Vehiculo con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el vehiculo. Microservicio Vehiculos no responde o ID inválido: " + e.getMessage());
        }
    }

    private void validarServicioEstetica(Long id) {
        try {
            var respuesta = esteticaProxy.obtenerServicioPorId(id);
            if (respuesta == null || respuesta.getStatusCode().isError()) {
                throw new RuntimeException("Servicio de estética con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el servicio de estética. Microservicio Estetica no responde o ID inválido: " + e.getMessage());
        }
    }

    public Optional<Cita> obtenerPorId(Long id) { return repository.findById(id); }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public Cita confirmarCita(Long id) {
        Cita cita = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
        
        if (Boolean.TRUE.equals(cita.getConfirmado())) {
            return cita;
        }

        cita.setConfirmado(true);
        cita = repository.save(cita);

        EstadoCitaHistorial log = new EstadoCitaHistorial();
        log.setFechaHora(LocalDateTime.now());
        log.setEstado("CONFIRMADA");
        log.setDetalle("Cita confirmada. Creando ticket de servicio correspondiente...");
        log.setCita(cita);
        historialRepository.save(log);

        if ("REPARACION".equalsIgnoreCase(cita.getTipoServicio())) {
            try {
                reparacionProxy.crearOrden(Map.of(
                    "vehiculoId", cita.getVehiculoId(),
                    "descripcion", cita.getDescripcionProblema(),
                    "citaId", cita.getId(),
                    "estado", "Pendiente"
                ));
            } catch (Exception e) {
                throw new RuntimeException("Cita confirmada pero falló la comunicación al microservicio de Reparaciones: " + e.getMessage());
            }
        } else if ("ESTETICA".equalsIgnoreCase(cita.getTipoServicio())) {
            try {
                esteticaProxy.crearReserva(Map.of(
                    "citaId", cita.getId(),
                    "vehiculoId", cita.getVehiculoId(),
                    "servicioEstetica", Map.of("id", cita.getServicioId()),
                    "estado", "Pendiente"
                ));
            } catch (Exception e) {
                throw new RuntimeException("Cita confirmada pero falló la comunicación al microservicio de Estética: " + e.getMessage());
            }
        }

        return cita;
    }

    @Transactional
    public Cita registrarFacturacion(Long id) {
        Cita cita = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        EstadoCitaHistorial log = new EstadoCitaHistorial();
        log.setFechaHora(LocalDateTime.now());
        log.setEstado("FACTURADA");
        log.setDetalle("Se ha emitido la factura de la cita.");
        log.setCita(cita);
        historialRepository.save(log);

        return cita;
    }

    @Transactional
    public Cita actualizarConfirmacion(Long id, Boolean confirmado) {
        if (Boolean.TRUE.equals(confirmado)) {
            return confirmarCita(id);
        }
        Cita cita = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
        cita.setConfirmado(confirmado);
        return repository.save(cita);
    }

    public List<Cita> buscarPorConfirmacion(Boolean confirmado) {
        return repository.findByConfirmado(confirmado);
    }
}
