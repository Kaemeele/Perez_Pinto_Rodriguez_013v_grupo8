package BullyCars.Estetica.Automotriz.Services;

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

import BullyCars.Estetica.Automotriz.Models.ServicioEstetica;
import BullyCars.Estetica.Automotriz.Repositories.EsteticaRepository;

@ExtendWith(MockitoExtension.class)
public class EsteticaServiceTest {

    @Mock
    private EsteticaRepository repository;

    @InjectMocks
    private EsteticaService esteticaService;

    private ServicioEstetica servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioEstetica();
        servicio.setId(1L);
        servicio.setNombre("Lavado Premium");
        servicio.setPrecio(20000.0);
    }

    @Test
    void guardar_Exito() {
        // Arrange
        when(repository.save(any(ServicioEstetica.class))).thenReturn(servicio);

        // Act
        ServicioEstetica resultado = esteticaService.guardar(servicio);

        // Assert
        assertNotNull(resultado);
        assertEquals("Lavado Premium", resultado.getNombre());
        verify(repository, times(1)).save(servicio);
    }

    @Test
    void eliminar_LanzaExceptionSiNoExiste() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> esteticaService.eliminar(1L));
        assertEquals("Servicio de estética no encontrado con ID: 1", exception.getMessage());
        verify(repository, never()).deleteById(anyLong());
    }
}
