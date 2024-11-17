package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;

@Repository

public interface PeticionUsuarioRepository extends JpaRepository<PeticionUsuario, Long> {
}
