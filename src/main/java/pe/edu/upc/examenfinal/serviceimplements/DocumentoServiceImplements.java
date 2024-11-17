package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Documento;
import pe.edu.upc.examenfinal.repositories.DocumentoRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.DocumentoService;

import java.util.List;
import java.util.Optional;

@Service

public class DocumentoServiceImplements implements DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Documento save(Documento documento) {
        return documentoRepository.save(documento);
    }

    @Override
    public List<Documento> list() {
        return documentoRepository.findAll();
    }

    @Override
    public Documento update(Long id, Documento updatedDocumento) {
        Optional<Documento> existingDocumentoOptional = documentoRepository.findById(id);
        if (existingDocumentoOptional.isPresent()) {
            Documento existingDocumento = existingDocumentoOptional.get();
            existingDocumento.setTitulo(updatedDocumento.getTitulo());
            existingDocumento.setDescripcion(updatedDocumento.getDescripcion());
            return documentoRepository.save(existingDocumento);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Documento> findById(Long id) {
        return documentoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        documentoRepository.deleteById(id);
    }
}
