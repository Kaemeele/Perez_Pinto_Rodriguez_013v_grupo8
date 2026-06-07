package BullyCars.Citas.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "estado_cita_historial")
@Data
public class EstadoCitaHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha y hora es obligatoria")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    private String detalle;

    @ManyToOne
    @JoinColumn(name = "cita_id")
    @JsonIgnore
    private Cita cita;
}
