package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class EspecialidadQueryDTO {
    private String especialidad_nombre;
    private String descripcion;
    private Long numero_colegiatura;
}
