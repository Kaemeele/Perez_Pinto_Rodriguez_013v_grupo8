package BullyCars.Citas.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock
    private CitaRepository repository;

    @Mock
    private EstadoCitaHistorialRepository historialRepository;

    @Mock
    private ClienteProxy clienteProxy;

    @Mock
    private VehiculoProxy vehiculoProxy;

    @Mock
    private ReparacionProxy reparacionProxy;

    @Mock
    private EsteticaProxy esteticaProxy;

    @InjectMocks
    private CitaService citaService;

    private Cita citaValida;

    @BeforeEach
    void setUp() {
        citaValida = new Cita();
        citaValida.setId(1L);
        citaValida.setClienteId(10L);
        citaValida.setVehiculoId(20L);
        citaValida.setFechaHora(LocalDateTime.now().plusDays(1)); // Mañana
        citaValida.setDescripcionProblema("Fallo en el motor");
        citaValida.setTipoServicio("REPARACION");
    }

    @Test
    void agendarCita_Exito() {
        // Arrange
        when(repository.findByVehiculoIdAndFechaHora(anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());
        
        when(clienteProxy.obtenerClientePorId(10L)).thenReturn(ResponseEntity.ok().build());
        when(vehiculoProxy.obtenerVehiculoPorId(20L)).thenReturn(ResponseEntity.ok().build());
        
        when(repository.save(any(Cita.class))).thenReturn(citaValida);
        when(historialRepository.save(any(EstadoCitaHistorial.class))).thenReturn(new EstadoCitaHistorial());

        // Act
        Cita resultado = citaService.agendar(citaValida);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(repository, times(1)).save(citaValida);
        verify(historialRepository, times(1)).save(any(EstadoCitaHistorial.class));
    }

    @Test
    void agendarCita_FechaPasada_LanzaException() {
        // Arrange
        citaValida.setFechaHora(LocalDateTime.now().minusDays(1)); // Ayer

        // Act & Assert
        assertThrows(FechaPasadaException.class, () -> citaService.agendar(citaValida));
        verify(repository, never()).save(any(Cita.class));
    }

    @Test
    void agendarCita_YaProgramada_LanzaException() {
        // Arrange
        when(repository.findByVehiculoIdAndFechaHora(anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(citaValida));

        // Act & Assert
        assertThrows(CitaYaProgramadaException.class, () -> citaService.agendar(citaValida));
        verify(repository, never()).save(any(Cita.class));
    }

    @Test
    void agendarCita_DatosIncompletos_LanzaException() {
        // Arrange
        citaValida.setDescripcionProblema(""); // Dato incompleto

        // Act & Assert
        assertThrows(CitaInvalidaException.class, () -> citaService.agendar(citaValida));
        verify(repository, never()).save(any(Cita.class));
    }
}
