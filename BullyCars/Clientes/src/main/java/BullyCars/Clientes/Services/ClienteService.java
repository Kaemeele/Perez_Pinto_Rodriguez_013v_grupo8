package BullyCars.Clientes.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;
import BullyCars.Clientes.Security.JwtUtil;
import jakarta.annotation.PostConstruct;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class ClienteService {

    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private ClienteRepository repository;

    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inyeccion automatica de dependencias de Spring
    @Autowired
    private JwtUtil jwtUtil;

    // Crea un Administrador Maestro por defecto al iniciar si la base de datos esta vacia
    @PostConstruct
    public void initAdmin() {
        if (repository.count() == 0) {
            Cliente admin = new Cliente();
            admin.setNombre("Administrador Maestro");
            admin.setEmail("admin@bullycars.cl");
            admin.setPassword(passwordEncoder.encode("qwerty123"));
            admin.setRol("ROLE_ADMIN"); 
            repository.save(admin);
            System.out.println(">>> Administrador inicial creado correctamente.");
        }
    }

    // Registra un nuevo cliente, encripta su contrasena y le asigna el rol de cliente
    public Cliente registrarCliente(Cliente cliente) {
        String claveEncriptada = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(claveEncriptada);
        cliente.setRol("ROLE_CLIENTE");
        
        return repository.save(cliente);
    }

    // Valida las credenciales de un cliente por email y contrasena y genera un token JWT si son correctas
    public String login(String email, String password) {
        Optional<Cliente> clienteOpt = repository.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                return jwtUtil.generarToken(cliente.getEmail(), cliente.getRol());
            }
        }
        throw new RuntimeException("Credenciales de acceso incorrectas.");
    }

    // Busca un cliente en la base de datos mediante su identificador unico ID
    public Cliente obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BullyCars.Clientes.Exceptions.ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado."));
    }

    // Busca un cliente en la base de datos mediante su direccion de correo electronico
    public Cliente obtenerPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new BullyCars.Clientes.Exceptions.ClienteNoEncontradoException("Cliente con correo " + email + " no encontrado."));
    }
}