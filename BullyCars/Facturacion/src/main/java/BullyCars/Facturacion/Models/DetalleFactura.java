package BullyCars.Facturacion.Models;

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

@Entity
@Table(name = "detalles_factura")
@Data
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción de la línea es obligatoria")
    private String descripcion;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    @JsonIgnore
    private Factura factura;
}
