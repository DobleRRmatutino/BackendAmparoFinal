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

public class CertificadoQueryDTO {
    private String titulo;
    private String descripcion;
}
