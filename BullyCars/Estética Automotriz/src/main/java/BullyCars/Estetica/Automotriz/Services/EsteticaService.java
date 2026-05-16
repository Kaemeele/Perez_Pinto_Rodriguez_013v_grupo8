package BullyCars.Estetica.Automotriz.Services;

public class EsteticaService {

}
@Service
public class EsteticaService {
    @Autowired private EsteticaRepository repo;
    public List<ServicioEstetica> listar() { return repo.findAll(); }
    public ServicioEstetica guardar(ServicioEstetica s) { return repo.save(s); }
}