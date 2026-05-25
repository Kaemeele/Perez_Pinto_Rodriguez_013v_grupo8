package BullyCars.Resenas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "resenas")
@Data
public class Resena {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    
    @Min(1) @Max(5)
    private Integer calificacion; // 1 a 5 estrellas

    @NotBlank(message = "El comentario no puede estar vacio")
    private String comentario;
}