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

public class SesionDTO {
    private Long id;
    private LocalDate fecha;
    private String titulo;
    private String descripcion;
    private String hora_inicio;
    private String hora_fin;
    private String link_sesion;
    private String link_grabacion;
    private String estado;
}
