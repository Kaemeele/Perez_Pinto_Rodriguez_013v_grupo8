package BullyCars.Vehiculos.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patente;
    private String marca;
    private String modelo;
    private int anio;

    @jakarta.persistence.OneToMany(mappedBy = "vehiculo", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private java.util.List<HistorialKilometraje> historialKilometraje;
}