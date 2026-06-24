package BullyCars.Resenas.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import BullyCars.Resenas.Models.Resena;
import BullyCars.Resenas.Proxy.CitaProxy;
import BullyCars.Resenas.Proxy.ClienteProxy;
import BullyCars.Resenas.Repositories.ResenaRepository;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository repository;

    @Mock
    private ClienteProxy clienteProxy;

    @Mock
    private CitaProxy citaProxy;

    @InjectMocks
    private ResenaService resenaService;

    private Resena resena;

    @BeforeEach
    void setUp() {
        resena = new Resena();
        resena.setId(1L);
        resena.setClienteId(10L);
        resena.setCalificacion(5);
        resena.setComentario("Excelente servicio");
    }

    @Test
    void guardar_Exito() {
        // Arrange
        when(clienteProxy.obtenerClientePorId(10L)).thenReturn(ResponseEntity.ok().build());
        
        List<Object> citasFalsas = List.of(Map.of("clienteId", 10L));
        when(citaProxy.listarTodas()).thenReturn(ResponseEntity.ok(citasFalsas));
        
        when(repository.save(any(Resena.class))).thenReturn(resena);

        // Act
        Resena resultado = resenaService.guardar(resena);

        // Assert
        assertNotNull(resultado);
        assertEquals(5, resultado.getCalificacion());
        verify(repository, times(1)).save(resena);
    }

    @Test
    void guardar_FallaSiClienteNoTieneCitas_LanzaException() {
        // Arrange
        when(clienteProxy.obtenerClientePorId(10L)).thenReturn(ResponseEntity.ok().build());
        
        List<Object> citasAjenas = List.of(Map.of("clienteId", 99L));
        when(citaProxy.listarTodas()).thenReturn(ResponseEntity.ok(citasAjenas));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> resenaService.guardar(resena));
        assertTrue(exception.getMessage().contains("no tiene citas registradas"));
        verify(repository, never()).save(any(Resena.class));
    }

    @Test
    void guardar_FallaSiClienteNoExiste_LanzaException() {
        // Arrange
        when(clienteProxy.obtenerClientePorId(10L)).thenThrow(new RuntimeException("Error red"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> resenaService.guardar(resena));
        assertTrue(exception.getMessage().contains("No se pudo validar el cliente"));
        verify(repository, never()).save(any(Resena.class));
    }
}
