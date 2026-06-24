package BullyCars.Facturacion.Repositories;

import BullyCars.Facturacion.Models.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
}
