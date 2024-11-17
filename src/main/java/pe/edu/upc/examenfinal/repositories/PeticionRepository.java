package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Peticion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface PeticionRepository extends JpaRepository<Peticion, Long> {
    @Query(value = "SELECT p.titulo, p.descripcion, p.tipo, p.fecha, p.estado FROM Peticion p WHERE p.fecha BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Object> PeticionIntervaloFechaQuery(LocalDate startDate, LocalDate endDate);


    @Query(value = "SELECT p.titulo, p.descripcion, p.tipo, p.fecha, p.estado FROM Peticion p WHERE p.tipo = :tipo", nativeQuery = true)
    List<Object> PeticionTipoQuery(String tipo);


    @Query(value = "SELECT p.titulo, p.descripcion, p.tipo, p.fecha, p.estado FROM Peticion p WHERE p.estado = :estado", nativeQuery = true)
    List<Object> PeticionEstadoQuery(String estado);

    @Query(value = "SELECT p.titulo, p.descripcion, p.tipo, p.fecha, p.estado FROM Peticion p WHERE p.id = :id", nativeQuery = true)
    Optional<Object> obtenerPeticionPorId(Long id);


}
