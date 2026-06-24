package BullyCars.Notificaciones.Models;

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
@Table(name = "logs_envio")
@Data
public class LogEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El canal es obligatorio (EMAIL/SMS/PUSH)")
    private String canal;

    @NotBlank(message = "El estado del envío es obligatorio")
    private String estado;

    @NotNull(message = "La fecha y hora del log es obligatoria")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    @JsonIgnore
    private Notificacion notificacion;
}
