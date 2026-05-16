package BullyCars.Notificaciones.Services;

public class NotificacionService {

}
@Service
public class NotificacionService {
    @Autowired private NotificacionRepository repo;
    public Notificacion registrar(Notificacion n) { return repo.save(n); }
    public List<Notificacion> verTodas() { return repo.findAll(); }
}