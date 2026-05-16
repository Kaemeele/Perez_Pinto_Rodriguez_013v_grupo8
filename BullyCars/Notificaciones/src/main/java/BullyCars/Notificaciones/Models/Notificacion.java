package BullyCars.Notificaciones.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String mensaje;
    private LocalDateTime fechaEnvio;

    @PrePersist
    public void prePersist() { this.fechaEnvio = LocalDateTime.now(); }
}