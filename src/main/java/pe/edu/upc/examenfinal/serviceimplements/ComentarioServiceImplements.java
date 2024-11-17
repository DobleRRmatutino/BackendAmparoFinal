package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Comentario;
import pe.edu.upc.examenfinal.repositories.ComentarioRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.ComentarioService;

import java.util.List;
import java.util.Optional;

@Service

public class ComentarioServiceImplements implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> list() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario update(Long id, Comentario updatedComentario) {
        Optional<Comentario> existingComentarioOptional = comentarioRepository.findById(id);
        if (existingComentarioOptional.isPresent()) {
            Comentario existingComentario = existingComentarioOptional.get();
            existingComentario.setDescription(updatedComentario.getDescription());
            existingComentario.setFecha(updatedComentario.getFecha());
            existingComentario.setAnonimo(updatedComentario.getAnonimo());
            return comentarioRepository.save(existingComentario);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        comentarioRepository.deleteById(id);
    }

}
