package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Comentario;
import pe.edu.upc.examenfinal.entities.Documento;

import java.util.List;
import java.util.Optional;

public interface DocumentoService {
    public Documento save(Documento documento);
    public List<Documento> list();
    public Documento update(Long id, Documento documento);
    public Optional<Documento> findById(Long id);
    public void delete(Long id);
}
