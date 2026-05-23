package BullyCars.Resenas.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "resenas")
@Data
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    
    @Min(1) @Max(5)
    private Integer calificacion; // 1 a 5 estrellas

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;
}