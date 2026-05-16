package BullyCars.Estetica.Automotriz.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity @Table(name = "servicios_estetica") @Data
public class ServicioEstetica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;
    @NotNull @Min(0)
    private Double precio;
}

@Service
public class EsteticaService {
    @Autowired private EsteticaRepository repo;
    public List<ServicioEstetica> listar() { return repo.findAll(); }
    public ServicioEstetica guardar(ServicioEstetica s) { return repo.save(s); }
}