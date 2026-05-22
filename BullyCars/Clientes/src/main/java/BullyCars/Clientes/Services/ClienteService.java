package BullyCars.Clientes.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;
import BullyCars.Clientes.Security.JwtUtil;
import jakarta.annotation.PostConstruct;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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

    public Cliente registrarCliente(Cliente cliente) {
        String claveEncriptada = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(claveEncriptada);
        cliente.setRol("ROLE_CLIENTE");
        
        return repository.save(cliente);
    }

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
}