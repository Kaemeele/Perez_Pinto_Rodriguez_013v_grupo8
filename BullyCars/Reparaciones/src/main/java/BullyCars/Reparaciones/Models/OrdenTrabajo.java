package BullyCars.Reparaciones.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "ordenes_trabajo")
@Data
public class OrdenTrabajo {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    private String estado; // Ejemplo: Pendiente, En Proceso, Terminado
    private Long vehiculoId; // Relacion logica via ID
    private String mecanicoAsignado;
}