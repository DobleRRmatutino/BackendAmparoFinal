package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RefugioDTO {
    private Long id;
    private String nombre;
    private String departamento;
    private String distrito;
    private String direccion;
    private String telefono;
}
