package BullyCars.Facturacion.Config;

import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Repositories.FacturaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final FacturaRepository facturaRepository;

    public DataSeeder(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (facturaRepository.count() == 0) {
            Factura factura = new Factura();
            factura.setCitaId(1L);
            factura.setTotal(40000.00);
            facturaRepository.save(factura);
        }
    }
}
