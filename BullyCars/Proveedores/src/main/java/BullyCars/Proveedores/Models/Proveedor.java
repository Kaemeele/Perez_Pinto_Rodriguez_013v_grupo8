package BullyCars.Proveedores.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entidad de persistencia (Modelo) que representa la tabla y estructura de datos en la base de datos.
 */
@Entity
@Table(name = "proveedores")
@Data
public class Proveedor {
    // Clave primaria identificadora de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombreEmpresa;

    private String contacto;
    private String categoriaInsumos; // Ejemplo: Neumaticos, Aceites
}