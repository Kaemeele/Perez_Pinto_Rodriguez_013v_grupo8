package BullyCars.Notificaciones.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "notificaciones")
@Data
public class Notificacion {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String mensaje;
    private LocalDateTime fechaEnvio;

    @PrePersist
    public void prePersist() { this.fechaEnvio = LocalDateTime.now(); }
}