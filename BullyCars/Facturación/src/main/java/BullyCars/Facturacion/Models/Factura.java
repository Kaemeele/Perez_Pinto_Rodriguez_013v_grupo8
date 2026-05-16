package BullyCars.Facturacion.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long citaId;
    private Double total;
    private LocalDateTime fechaEmision;

    @PrePersist
    public void prePersist() { this.fechaEmision = LocalDateTime.now(); }
}