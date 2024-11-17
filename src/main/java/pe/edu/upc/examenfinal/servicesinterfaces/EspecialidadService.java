package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Documento;
import pe.edu.upc.examenfinal.entities.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    public Especialidad save(Especialidad especialidad);
    public List<Especialidad> list();
    public Especialidad update(Long id, Especialidad especialidad);
    public Optional<Especialidad> findById(Long id);
    public void delete(Long id);
    public List<Object> EspecialidadQuery1(String nombre);
    List<Especialidad> obtenerEspecialidadesPorUsuario(Long usuarioId);
}
