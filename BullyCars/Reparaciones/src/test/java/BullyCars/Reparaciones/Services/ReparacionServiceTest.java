package BullyCars.Reparaciones.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import BullyCars.Reparaciones.Models.OrdenTrabajo;
import BullyCars.Reparaciones.Proxy.InventarioProxy;
import BullyCars.Reparaciones.Proxy.VehiculoProxy;
import BullyCars.Reparaciones.Repositories.OrdenTrabajoRepository;
import BullyCars.Reparaciones.Repositories.RepuestoUsadoRepository;

@ExtendWith(MockitoExtension.class)
public class ReparacionServiceTest {

    @Mock
    private OrdenTrabajoRepository repository;

    @Mock
    private RepuestoUsadoRepository repuestoUsadoRepository;

    @Mock
    private VehiculoProxy vehiculoProxy;

    @Mock
    private InventarioProxy inventarioProxy;

    @InjectMocks
    private ReparacionService reparacionService;

    private OrdenTrabajo orden;

    @BeforeEach
    void setUp() {
        orden = new OrdenTrabajo();
        orden.setId(1L);
        orden.setVehiculoId(20L);
        orden.setEstado("Pendiente");
        orden.setCostoManoObra(50000.0);
    }

    @Test
    void crearOrden_Exito() {
        // Arrange
        when(vehiculoProxy.obtenerVehiculoPorId(20L)).thenReturn(ResponseEntity.ok().build());
        when(repository.save(any(OrdenTrabajo.class))).thenReturn(orden);

        // Act
        OrdenTrabajo resultado = reparacionService.crearOrden(orden);

        // Assert
        assertNotNull(resultado);
        assertEquals("Pendiente", resultado.getEstado());
        verify(repository, times(1)).save(orden);
    }

    @Test
    void crearOrden_FallaSiVehiculoNoExiste_LanzaException() {
        // Arrange
        when(vehiculoProxy.obtenerVehiculoPorId(20L)).thenThrow(new RuntimeException("Microservicio caido"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> reparacionService.crearOrden(orden));
        assertTrue(exception.getMessage().contains("Error al validar el vehiculo"));
        verify(repository, never()).save(any(OrdenTrabajo.class));
    }
}
