package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Especialidad;
import pe.edu.upc.examenfinal.entities.Peticion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PeticionService {
    public Peticion save(Peticion peticion);

    public List<Peticion> list();

    public Peticion update(Long id, Peticion peticion);

    public Optional<Peticion> findById(Long id);
    public void delete(Long id);
    public List<Object> PeticionIntervaloFechaQuery(LocalDate startDate, LocalDate endDate);

    public List<Object> PeticionTipoQuery(String tipo);

    public List<Object> PeticionEstadoQuery(String estado);

    Optional<Object> obtenerPeticionPorId(Long id);
}