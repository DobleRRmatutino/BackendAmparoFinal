package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Especialidad;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    @Query(value = "SELECT e.especialidad_nombre, e.descripcion, e.numero_colegiatura FROM Especialidad e WHERE e.especialidad_nombre = :nombre", nativeQuery = true)
    List<Object> EspecialidadQuery1(String nombre);

    @Query("SELECT e FROM Especialidad e JOIN e.usuario u WHERE u.id = :usuarioId")
    List<Especialidad> obtenerEspecialidadesPorUsuarioId(@Param("usuarioId") Long usuarioId);

}
