package BullyCars.Vehiculos.Services;

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

import BullyCars.Vehiculos.Models.Vehiculo;
import BullyCars.Vehiculos.Repositories.VehiculoRepository;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock
    private VehiculoRepository repository;

    @InjectMocks
    private VehiculoService vehiculoService;

    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setPatente("ABC-123");
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
    }

    @Test
    void guardarVehiculo_Exito() {
        // Arrange
        when(repository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        // Act
        Vehiculo resultado = vehiculoService.guardar(vehiculo);

        // Assert
        assertNotNull(resultado);
        assertEquals("ABC-123", resultado.getPatente());
        verify(repository, times(1)).save(vehiculo);
    }

    @Test
    void obtenerPorId_Exito() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(vehiculo));

        // Act
        Vehiculo resultado = vehiculoService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Toyota", resultado.getMarca());
        verify(repository, times(1)).findById(1L);
    }
    
    @Test
    void eliminar_LanzaExceptionSiNoExiste() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(BullyCars.Vehiculos.Exceptions.VehiculoNoEncontradoException.class, () -> vehiculoService.eliminar(1L));
        assertEquals("Vehículo no encontrado con ID: 1", exception.getMessage());
        verify(repository, never()).deleteById(anyLong());
    }
}
