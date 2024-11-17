package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SesionQueryDTO {
    private LocalDate fecha;
    private String titulo;
    private String descripcion;
    private String horaInicio;
    private String horaFinal;
    private String linkSesion;
    private String linkGrabacion;
}
