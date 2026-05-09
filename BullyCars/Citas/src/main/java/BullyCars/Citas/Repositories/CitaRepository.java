package BullyCars.Citas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BullyCars.Citas.Models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
}