package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Sesion;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    @Query("SELECT s.fecha as fecha, s.titulo as titulo, s.descripcion as descripcion, s.hora_inicio as hora_inicio, s.hora_fin as hora_final, s.link_sesion as link_sesion, s.link_grabacion as link_grabacion FROM Sesion s WHERE s.fecha BETWEEN :startDate AND :endDate")
    List<Object> SesionFechaIntervaloQuery(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
