package BullyCars.inventario.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Data
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @NotBlank(message = "El tipo de movimiento es obligatorio (ENTRADA/SALIDA)")
    private String tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "repuesto_id")
    @JsonIgnore
    private Repuesto repuesto;
}
