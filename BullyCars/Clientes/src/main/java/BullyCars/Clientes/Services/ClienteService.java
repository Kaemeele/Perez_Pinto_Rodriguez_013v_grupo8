package BullyCars.Clientes.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;
import BullyCars.Clientes.Security.JwtUtil; // <- IMPORTAR UTILERÍA

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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
            // Compara la clave enviada contra el Hash Bcrypt guardado en la BD
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                // Emitimos el token JWT real firmado criptográficamente
                return jwtUtil.generarToken(cliente.getEmail(), cliente.getRol());
            }
        }
        throw new RuntimeException("Credenciales de acceso incorrectas.");
    }
}