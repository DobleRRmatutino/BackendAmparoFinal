package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Certificado;
import pe.edu.upc.examenfinal.entities.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    public Comentario save(Comentario comentario);
    public List<Comentario> list();
    public Comentario update(Long id, Comentario comentario);
    public Optional<Comentario> findById(Long id);
    public void delete(Long id);
}
