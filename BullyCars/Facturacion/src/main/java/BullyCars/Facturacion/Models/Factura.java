package BullyCars.Facturacion.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

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

    @jakarta.persistence.OneToMany(mappedBy = "factura", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private java.util.List<DetalleFactura> detalles;
}