package BullyCars.Notificaciones.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import BullyCars.Notificaciones.Models.Notificacion;
import BullyCars.Notificaciones.Repositories.NotificacionRepository;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService notificacionService;

    private Notificacion notificacion;

    @BeforeEach
    void setUp() {
        notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setClienteId(10L);
        notificacion.setMensaje("Test de notificación");
        notificacion.setFechaEnvio(LocalDateTime.now());
    }

    @Test
    void registrarNotificacion_Exito() {
        // Arrange
        when(repository.save(any(Notificacion.class))).thenReturn(notificacion);

        // Act
        Notificacion resultado = notificacionService.registrar(notificacion);

        // Assert
        assertNotNull(resultado);
        assertEquals("Test de notificación", resultado.getMensaje());
        verify(repository, times(1)).save(notificacion);
    }
}
