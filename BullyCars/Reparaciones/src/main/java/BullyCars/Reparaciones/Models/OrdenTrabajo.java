package BullyCars.Reparaciones.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "ordenes_trabajo")
@Data
public class OrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private String estado; // Ejemplo: Pendiente, En Proceso, Terminado
    private Long vehiculoId; // Relación lógica vía ID
    private String mecanicoAsignado;
}