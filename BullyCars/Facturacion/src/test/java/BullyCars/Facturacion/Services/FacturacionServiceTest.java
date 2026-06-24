package BullyCars.Facturacion.Services;

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

import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Repositories.FacturaRepository;

@ExtendWith(MockitoExtension.class)
public class FacturacionServiceTest {

    @Mock
    private FacturaRepository repository;

    @InjectMocks
    private FacturacionService facturacionService;

    private Factura factura;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setId(1L);
        factura.setCitaId(10L);
        factura.setTotal(15000.0);
    }

    @Test
    void generar_Exito() {
        // Arrange
        when(repository.save(any(Factura.class))).thenReturn(factura);

        // Act
        Factura resultado = facturacionService.generar(factura);

        // Assert
        assertNotNull(resultado);
        assertEquals(15000.0, resultado.getTotal());
        verify(repository, times(1)).save(factura);
    }

    @Test
    void eliminar_LanzaExceptionSiNoExiste() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> facturacionService.eliminar(1L));
        assertEquals("Factura no encontrada con ID: 1", exception.getMessage());
        verify(repository, never()).deleteById(anyLong());
    }
}
