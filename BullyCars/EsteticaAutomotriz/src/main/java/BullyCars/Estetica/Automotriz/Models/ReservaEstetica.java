package BullyCars.Estetica.Automotriz.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "reservas_estetica")
@Data
public class ReservaEstetica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El citaId es obligatorio")
    private Long citaId;

    @NotNull(message = "El vehiculoId es obligatorio")
    private Long vehiculoId;

    private String estado = "Pendiente"; // e.g. Pendiente, Completado

    @ManyToOne
    @JoinColumn(name = "servicio_estetica_id")
    private ServicioEstetica servicioEstetica;
}
