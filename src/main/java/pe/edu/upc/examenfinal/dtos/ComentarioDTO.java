package pe.edu.upc.examenfinal.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.examenfinal.entities.Users;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ComentarioDTO {
    private Long id;
    private String description;
    private LocalDate fecha;
    private Boolean anonimo;

    @JsonIgnore
    private Users usuario;

}
