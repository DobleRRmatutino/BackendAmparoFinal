package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Certificado;
import pe.edu.upc.examenfinal.entities.Especialidad;

import java.util.List;

@Repository

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    @Query(value = "SELECT c.titulo , c.descripcion FROM Certificado c WHERE c.titulo = :nombre", nativeQuery = true)
    List<Object> CertificadoQuery1(String nombre);
}


