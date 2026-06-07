package BullyCars.Citas.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String descripcionProblema;
    
    private Long clienteId;
    private Long vehiculoId;
    private Boolean confirmado = false;

    private String tipoServicio = "REPARACION"; // e.g. REPARACION, ESTETICA
    private Long servicioId; // refers to ServicioEstetica catalog if tipoServicio is ESTETICA

    @jakarta.persistence.OneToMany(mappedBy = "cita", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private java.util.List<EstadoCitaHistorial> historialEstados;
}
