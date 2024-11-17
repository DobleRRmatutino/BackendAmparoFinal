package pe.edu.upc.examenfinal.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.examenfinal.entities.Refugio;
import pe.edu.upc.examenfinal.entities.Sesion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SesionService {
    public Sesion save(Sesion sesion);
    public List<Sesion> list();
    public Sesion update(Long id, Sesion sesion);
    public Optional<Sesion> findById(Long id);
    public List<Object> SesionFechaIntervaloQuery(LocalDate startDate, LocalDate endDate);
    public void delete(Long id);

}
