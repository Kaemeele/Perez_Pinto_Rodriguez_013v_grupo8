package BullyCars.Vehiculos.Models;

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
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_kilometraje")
@Data
public class HistorialKilometraje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @NotNull(message = "El kilometraje es obligatorio")
    private Integer kilometraje;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonIgnore
    private Vehiculo vehiculo;
}
