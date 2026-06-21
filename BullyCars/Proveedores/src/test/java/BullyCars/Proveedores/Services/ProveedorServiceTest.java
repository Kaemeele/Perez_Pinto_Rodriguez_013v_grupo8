package BullyCars.Proveedores.Services;

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

import BullyCars.Proveedores.Models.Proveedor;
import BullyCars.Proveedores.Repositories.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository repository;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombreEmpresa("Proveedor Test");
        proveedor.setContacto("12345678-9");
    }

    @Test
    void guardar_Exito() {
        // Arrange
        when(repository.save(any(Proveedor.class))).thenReturn(proveedor);

        // Act
        Proveedor resultado = proveedorService.guardar(proveedor);

        // Assert
        assertNotNull(resultado);
        assertEquals("Proveedor Test", resultado.getNombreEmpresa());
        verify(repository, times(1)).save(proveedor);
    }

    @Test
    void eliminar_LanzaExceptionSiNoExiste() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> proveedorService.eliminar(1L));
        assertEquals("Proveedor no encontrado con ID: 1", exception.getMessage());
        verify(repository, never()).deleteById(anyLong());
    }
}
