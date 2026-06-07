package BullyCars.inventario.Services;

import java.util.Optional;

import BullyCars.inventario.Models.Repuesto;
import BullyCars.inventario.Repositories.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioService {
    
    @Autowired
    private RepuestoRepository repository;

    @Autowired
    private BullyCars.inventario.Repositories.MovimientoInventarioRepository movimientoRepository;

    public List<Repuesto> listarTodo() {
        return repository.findAll();
    }

    public Repuesto guardar(Repuesto repuesto) {
        return repository.save(repuesto);
    }

    public Optional<Repuesto> obtenerPorId(Long id) { return repository.findById(id); }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Repuesto no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @org.springframework.transaction.annotation.Transactional
    public Repuesto descontarStock(Long id, Integer cantidad, String descripcion) {
        Repuesto repuesto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado con ID: " + id));

        if (repuesto.getStock() < cantidad) {
            throw new BullyCars.inventario.Exceptions.SinStockException(
                    "Stock insuficiente para el repuesto: " + repuesto.getNombre() + ". Stock disponible: " + repuesto.getStock()
            );
        }

        repuesto.setStock(repuesto.getStock() - cantidad);
        repuesto = repository.save(repuesto);

        BullyCars.inventario.Models.MovimientoInventario movimiento = new BullyCars.inventario.Models.MovimientoInventario();
        movimiento.setFecha(java.time.LocalDateTime.now());
        movimiento.setTipoMovimiento("SALIDA");
        movimiento.setCantidad(cantidad);
        movimiento.setDescripcion(descripcion != null ? descripcion : "Descuento de stock");
        movimiento.setRepuesto(repuesto);
        movimientoRepository.save(movimiento);

        return repuesto;
    }

    @org.springframework.transaction.annotation.Transactional
    public Repuesto agregarStock(Long id, Integer cantidad, String descripcion) {
        Repuesto repuesto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado con ID: " + id));

        repuesto.setStock(repuesto.getStock() + cantidad);
        repuesto = repository.save(repuesto);

        BullyCars.inventario.Models.MovimientoInventario movimiento = new BullyCars.inventario.Models.MovimientoInventario();
        movimiento.setFecha(java.time.LocalDateTime.now());
        movimiento.setTipoMovimiento("ENTRADA");
        movimiento.setCantidad(cantidad);
        movimiento.setDescripcion(descripcion != null ? descripcion : "Ingreso de stock");
        movimiento.setRepuesto(repuesto);
        movimientoRepository.save(movimiento);

        return repuesto;
    }
}