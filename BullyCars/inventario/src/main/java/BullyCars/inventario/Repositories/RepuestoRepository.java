package BullyCars.Inventario.Repositories;

import BullyCars.Inventario.Models.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
}