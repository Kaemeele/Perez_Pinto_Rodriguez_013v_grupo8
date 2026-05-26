package BullyCars.Estetica.Automotriz.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity @Table(name = "servicios_estetica") @Data
public class ServicioEstetica {
    // Clave primaria identificadora de la entidad
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;
    @NotNull @Min(0)
    private Double precio;
}