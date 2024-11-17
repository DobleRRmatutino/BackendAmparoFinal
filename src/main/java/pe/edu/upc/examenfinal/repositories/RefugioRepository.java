package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Refugio;

import java.util.List;

@Repository

public interface RefugioRepository extends JpaRepository<Refugio, Long> {
    @Query(value = "SELECT r.nombre AS nombre, r.departamento AS departamento, r.distrito AS distrito, r.direccion AS direccion, r.telefono AS telefono FROM refugio r WHERE r.distrito = :distrito", nativeQuery = true)
    List<Object> RefugioDistritoQuery(@Param("distrito") String distrito);


    @Query(value = "SELECT r.nombre AS nombre, r.departamento AS departamento, r.distrito AS distrito, r.direccion AS direccion, r.telefono AS telefono FROM refugio r WHERE r.departamento = :departamento", nativeQuery = true)
    List<Object> RefugioDepartamentoQuey(@Param("departamento") String departamento);

}
