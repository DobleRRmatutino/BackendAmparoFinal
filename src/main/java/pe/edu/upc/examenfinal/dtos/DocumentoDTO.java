package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.examenfinal.entities.Peticion;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DocumentoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private Peticion peticion;
}
