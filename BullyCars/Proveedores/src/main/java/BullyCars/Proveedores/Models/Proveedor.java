package BullyCars.Proveedores.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombreEmpresa;

    private String contacto;
    private String categoriaInsumos; // Ejemplo: Neumáticos, Aceites
}