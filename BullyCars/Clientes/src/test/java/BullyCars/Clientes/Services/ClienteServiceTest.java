package BullyCars.Clientes.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;
import BullyCars.Clientes.Security.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Test User");
        cliente.setEmail("test@example.com");
        cliente.setPassword("rawPassword");
    }

    @Test
    void registrarClienteDeberiaEncriptarClaveYAsignarRol() {
        // Given
        when(passwordEncoder.encode("rawPassword")).thenReturn("encryptedPassword");
        when(repository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Cliente result = service.registrarCliente(cliente);

        // Then
        assertNotNull(result);
        assertEquals("encryptedPassword", result.getPassword());
        assertEquals("ROLE_CLIENTE", result.getRol());
        verify(passwordEncoder, times(1)).encode("rawPassword");
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void loginDeberiaRetornarTokenCuandoCredencialesSonCorrectas() {
        // Given
        cliente.setPassword("encryptedPassword");
        cliente.setRol("ROLE_CLIENTE");
        when(repository.findAll()).thenReturn(List.of(cliente));
        when(passwordEncoder.matches("rawPassword", "encryptedPassword")).thenReturn(true);
        when(jwtUtil.generarToken("test@example.com", "ROLE_CLIENTE")).thenReturn("mockToken");

        // When
        String token = service.login("test@example.com", "rawPassword");

        // Then
        assertNotNull(token);
        assertEquals("mockToken", token);
        verify(repository, times(1)).findAll();
        verify(passwordEncoder, times(1)).matches("rawPassword", "encryptedPassword");
        verify(jwtUtil, times(1)).generarToken("test@example.com", "ROLE_CLIENTE");
    }

    @Test
    void loginDeberiaLanzarExcepcionCuandoCredencialesSonIncorrectas() {
        // Given
        cliente.setPassword("encryptedPassword");
        when(repository.findAll()).thenReturn(List.of(cliente));
        when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false);

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.login("test@example.com", "wrongPassword");
        });

        assertEquals("Credenciales de acceso incorrectas.", exception.getMessage());
        verify(jwtUtil, never()).generarToken(anyString(), anyString());
    }

    @Test
    void obtenerPorIdDeberiaRetornarClienteCuandoExiste() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        // When
        Cliente result = service.obtenerPorId(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).findById(1L);
    }
}
