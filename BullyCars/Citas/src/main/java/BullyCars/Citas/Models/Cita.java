package BullyCars.Citas.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "citas")
@Data
public class Cita {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String descripcionProblema;
    
    private Long clienteId;
    private Long vehiculoId;
}