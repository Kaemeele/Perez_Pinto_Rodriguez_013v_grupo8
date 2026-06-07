package BullyCars.Reparaciones.Repositories;

import BullyCars.Reparaciones.Models.RepuestoUsado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoUsadoRepository extends JpaRepository<RepuestoUsado, Long> {
}
