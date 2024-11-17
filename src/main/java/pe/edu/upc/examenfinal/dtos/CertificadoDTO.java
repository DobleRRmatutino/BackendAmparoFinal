package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.examenfinal.entities.Especialidad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CertificadoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Especialidad especialidad;
}
