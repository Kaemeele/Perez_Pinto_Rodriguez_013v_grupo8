package BullyCars.Reparaciones.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "repuestos_usados")
@Data
public class RepuestoUsado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El repuestoId es obligatorio")
    private Long repuestoId;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @NotNull(message = "El precioUnitario es obligatorio")
    private Double precioUnitario;

    private String nombreRepuesto;

    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id")
    @JsonIgnore
    private OrdenTrabajo ordenTrabajo;
}
