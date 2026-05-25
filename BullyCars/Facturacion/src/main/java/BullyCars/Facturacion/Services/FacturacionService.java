package BullyCars.Facturacion.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Repositories.FacturaRepository;

/**
 * Servicio que encapsula la logica de negocio principal y las reglas operacionales.
 */
@Service
public class FacturacionService {
    // Inyeccion automatica de dependencias de Spring
    @Autowired private FacturaRepository repo;

    // Genera y registra una nueva factura de venta o cobro en la base de datos
    public Factura generar(Factura f) { return repo.save(f); }

    // Obtiene el historial completo de facturas registradas en el sistema
    public List<Factura> historial() { return repo.findAll(); }
}
