package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PeticionQueryDTO {
    private String titulo;
    private String descripcion;
    private String tipo;
    private LocalDate fecha;
    private String estado;
}
