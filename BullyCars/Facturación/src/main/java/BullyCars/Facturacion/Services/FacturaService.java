package BullyCars.Facturacion.Services;

public class FacturaService {

}
@Service
public class FacturacionService {
    @Autowired private FacturaRepository repo;
    public Factura generar(Factura f) { return repo.save(f); }
    public List<Factura> historial() { return repo.findAll(); }
}