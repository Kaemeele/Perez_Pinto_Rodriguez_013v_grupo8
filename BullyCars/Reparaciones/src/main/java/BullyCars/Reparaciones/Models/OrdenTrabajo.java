package BullyCars.Reparaciones.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "ordenes_trabajo")
@Data
public class OrdenTrabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    private String estado; 
    private Long vehiculoId; 
    private String mecanicoAsignado;
    
    private Long citaId;
    private Double costoManoObra = 0.0;

    @OneToMany(mappedBy = "ordenTrabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<RepuestoUsado> repuestosUsados;
}