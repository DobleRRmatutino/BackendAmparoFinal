package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.examenfinal.entities.Users;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EspecialidadDTO {
    private Long id;
    private String especialidad_nombre;
    private String numero_colegiatura;
    private String descripcion;
    private Users usuario;
}
