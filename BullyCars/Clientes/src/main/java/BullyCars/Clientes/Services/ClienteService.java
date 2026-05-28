package BullyCars.Clientes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;
import BullyCars.Clientes.Security.JwtUtil;

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
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                return jwtUtil.generarToken(cliente.getEmail(), cliente.getRol());
            }
        }
        throw new RuntimeException("Credenciales de acceso incorrectas.");
    }

    public Cliente obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BullyCars.Clientes.Exceptions.ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado."));
    }

    public Cliente obtenerPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new BullyCars.Clientes.Exceptions.ClienteNoEncontradoException("Cliente con correo " + email + " no encontrado."));
    }
}