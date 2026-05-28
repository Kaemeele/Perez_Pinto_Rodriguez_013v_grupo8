package BullyCars.Clientes.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import BullyCars.Clientes.Models.Cliente;
import BullyCars.Clientes.Repositories.ClienteRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            Cliente admin = new Cliente();
            admin.setNombre("Administrador Maestro");
            admin.setEmail("admin@bullycars.cl");
            admin.setPassword(passwordEncoder.encode("qwerty123"));
            admin.setRol("ROLE_ADMIN");
            clienteRepository.save(admin);
            System.out.println(">>> Administrador inicial creado correctamente via DataSeeder.");
        }
    }
}
