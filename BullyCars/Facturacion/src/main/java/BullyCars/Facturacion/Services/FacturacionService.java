package BullyCars.Facturacion.Services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BullyCars.Facturacion.Models.Factura;
import BullyCars.Facturacion.Repositories.FacturaRepository;

@Service
public class FacturacionService {
    
    @Autowired private FacturaRepository repo;

    public Factura generar(Factura f) { return repo.save(f); }

    public List<Factura> historial() { return repo.findAll(); }

    public Optional<Factura> obtenerPorId(Long id) { return repo.findById(id); }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
        repo.deleteById(id);
    }
}

