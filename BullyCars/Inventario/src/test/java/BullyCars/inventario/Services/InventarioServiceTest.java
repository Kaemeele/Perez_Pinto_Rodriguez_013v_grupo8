package BullyCars.inventario.Services;

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

import BullyCars.inventario.Exceptions.SinStockException;
import BullyCars.inventario.Models.MovimientoInventario;
import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.MovimientoInventarioRepository;
import BullyCars.inventario.Repositories.RepuestoRepository;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private RepuestoRepository repository;

    @Mock
    private MovimientoInventarioRepository movimientoRepository;

    @InjectMocks
    private InventarioService inventarioService;

    private Repuesto repuesto;

    @BeforeEach
    void setUp() {
        repuesto = new Repuesto();
        repuesto.setId(1L);
        repuesto.setNombre("Filtro de Aceite");
        repuesto.setStock(10);
    }

    @Test
    void descontarStock_Exito() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(repuesto));
        when(repository.save(any(Repuesto.class))).thenReturn(repuesto);
        when(movimientoRepository.save(any(MovimientoInventario.class))).thenReturn(new MovimientoInventario());

        // Act
        Repuesto resultado = inventarioService.descontarStock(1L, 2, "Venta");

        // Assert
        assertNotNull(resultado);
        assertEquals(8, resultado.getStock());
        verify(repository, times(1)).save(repuesto);
        verify(movimientoRepository, times(1)).save(any(MovimientoInventario.class));
    }

    @Test
    void descontarStock_SinStock_LanzaException() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(repuesto));

        // Act & Assert
        assertThrows(SinStockException.class, () -> inventarioService.descontarStock(1L, 15, "Venta masiva"));
        verify(repository, never()).save(any(Repuesto.class));
        verify(movimientoRepository, never()).save(any(MovimientoInventario.class));
    }

    @Test
    void agregarStock_Exito() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(repuesto));
        when(repository.save(any(Repuesto.class))).thenReturn(repuesto);
        when(movimientoRepository.save(any(MovimientoInventario.class))).thenReturn(new MovimientoInventario());

        // Act
        Repuesto resultado = inventarioService.agregarStock(1L, 5, "Compra a proveedor");

        // Assert
        assertNotNull(resultado);
        assertEquals(15, resultado.getStock());
        verify(repository, times(1)).save(repuesto);
        verify(movimientoRepository, times(1)).save(any(MovimientoInventario.class));
    }
}
