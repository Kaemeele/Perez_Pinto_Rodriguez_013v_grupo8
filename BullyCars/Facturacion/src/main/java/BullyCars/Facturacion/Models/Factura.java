package BullyCars.Facturacion.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "facturas")
@Data
public class Factura {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long citaId;
    private Double total;
    private LocalDateTime fechaEmision;

    @PrePersist
    public void prePersist() { this.fechaEmision = LocalDateTime.now(); }
}