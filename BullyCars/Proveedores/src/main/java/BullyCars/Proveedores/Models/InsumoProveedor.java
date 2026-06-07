package BullyCars.Proveedores.Models;

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
@Table(name = "insumos_proveedores")
@Data
public class InsumoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del insumo es obligatorio")
    private String nombreInsumo;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    @JsonIgnore
    private Proveedor proveedor;
}
