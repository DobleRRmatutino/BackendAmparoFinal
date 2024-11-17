package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.entities.Users;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PeticionUsuarioDTO {
    private Long id;
    private LocalDate fecha;
    private Users users;
    private Peticion peticion;
}
