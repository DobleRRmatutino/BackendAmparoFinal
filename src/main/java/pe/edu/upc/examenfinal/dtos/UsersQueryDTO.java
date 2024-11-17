package pe.edu.upc.examenfinal.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsersQueryDTO {
    private Long id;
    private String nombreUsuario;
}
