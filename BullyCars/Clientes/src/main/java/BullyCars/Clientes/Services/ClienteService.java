package BullyCars.Clientes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; // <- IMPORTAR

import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el componente criptográfico

    public Cliente registrarCliente(Cliente cliente) {
        // Encriptar la contraseña con BCrypt antes de persistir
        String claveEncriptada = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(claveEncriptada);
        
        // Asignar rol por defecto si viene vacío
        if (cliente.getRol() == null || cliente.getRol().isEmpty()) {
            cliente.setRol("ROLE_CLIENTE");
        }
        
        return repository.save(cliente);
    }

    public String login(String email, String password) {
        Optional<Cliente> clienteOpt = repository.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            // Validar si la contraseña ingresada coincide con el hash de la Base de Datos
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                // Aquí se generará el token JWT. Por ahora retornamos una simulación limpia para tu pre-entrega:
                return "JWT_TOKEN_SIMULADO_VALIDO_PARA_ROL_" + cliente.getRol();
            }
        }
        throw new RuntimeException("Credenciales de acceso incorrectas.");
    }
}