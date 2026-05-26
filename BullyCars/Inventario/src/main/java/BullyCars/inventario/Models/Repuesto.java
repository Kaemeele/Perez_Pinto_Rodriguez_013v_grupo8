package BullyCars.inventario.Models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "repuestos")
@Data
public class Repuesto {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer stock;
    private Double precio;
}