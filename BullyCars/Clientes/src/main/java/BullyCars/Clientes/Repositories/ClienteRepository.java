package BullyCars.Clientes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BullyCars.Clientes.Models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}