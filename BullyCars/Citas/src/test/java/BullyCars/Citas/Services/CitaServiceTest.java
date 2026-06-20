package BullyCars.Citas.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock private CitaRepository repository;
    @Mock private EstadoCitaHistorialRepository historialRepository;
    @Mock private ClienteProxy clienteProxy;
    @Mock private VehiculoProxy vehiculoProxy;
    @Mock private ReparacionProxy reparacionProxy;
    @Mock private EsteticaProxy esteticaProxy;

    @InjectMocks
    private CitaService service;

    private Cita cita;

    @BeforeEach
    void setUp() {
        cita = new Cita();
        cita.setId(1L);
        cita.setClienteId(10L);
        cita.setVehiculoId(20L);
        cita.setFechaHora(LocalDateTime.now().plusDays(2)); // Fecha futura
        cita.setDescripcionProblema("Cambio de aceite");
        cita.setTipoServicio("REPARACION");
    }

    @Test
    void agendarCitaDeberiaGuardarCorrectamente() {
        // Given
        when(clienteProxy.obtenerClientePorId(10L)).thenReturn(ResponseEntity.ok(new Object()));
        when(vehiculoProxy.obtenerVehiculoPorId(20L)).thenReturn(ResponseEntity.ok(new Object()));
        when(repository.findByVehiculoIdAndFechaHora(20L, cita.getFechaHora())).thenReturn(Optional.empty());
        when(repository.save(any(Cita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Cita result = service.agendar(cita);

        // Then
        assertNotNull(result);
        assertEquals("REPARACION", result.getTipoServicio());
        verify(repository, times(1)).save(any(Cita.class));
        verify(historialRepository, times(1)).save(any(EstadoCitaHistorial.class));
    }

    @Test
    void agendarCitaDeberiaLanzarFechaPasadaException() {
        // Given
        cita.setFechaHora(LocalDateTime.now().minusDays(1)); // Fecha pasada

        // When & Then
        assertThrows(FechaPasadaException.class, () -> {
            service.agendar(cita);
        });
        verify(repository, never()).save(any(Cita.class));
    }

    @Test
    void agendarCitaDeberiaLanzarCitaYaProgramadaException() {
        // Given
        when(repository.findByVehiculoIdAndFechaHora(20L, cita.getFechaHora())).thenReturn(Optional.of(new Cita()));

        // When & Then
        assertThrows(CitaYaProgramadaException.class, () -> {
            service.agendar(cita);
        });
        verify(repository, never()).save(any(Cita.class));
    }

    @Test
    void agendarCitaDeberiaLanzarRuntimeExceptionSiClienteNoExiste() {
        // Given
        when(clienteProxy.obtenerClientePorId(10L)).thenReturn(ResponseEntity.status(404).body(null));

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.agendar(cita);
        });
        assertTrue(exception.getMessage().contains("Cliente con ID 10 no existe") || exception.getMessage().contains("No se pudo validar el cliente"));
        verify(repository, never()).save(any(Cita.class));
    }
}
