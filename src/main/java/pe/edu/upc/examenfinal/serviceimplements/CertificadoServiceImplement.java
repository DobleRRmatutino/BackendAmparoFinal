package pe.edu.upc.examenfinal.serviceimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Certificado;
import pe.edu.upc.examenfinal.repositories.CertificadoRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.CertificadoService;

import java.util.List;
import java.util.Optional;

@Service

public class CertificadoServiceImplement implements CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Override
    public Certificado save(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    @Override
    public List<Certificado> list() {
        return certificadoRepository.findAll();
    }

    @Override
    public Certificado update(Long id, Certificado updatedCertificado) {
        Optional<Certificado> existingCertificadoOptional = certificadoRepository.findById(id);
        if (existingCertificadoOptional.isPresent()) {
            Certificado existingCertificado = existingCertificadoOptional.get();
            existingCertificado.setTitulo(updatedCertificado.getTitulo());
            existingCertificado.setDescripcion(updatedCertificado.getDescripcion());
            return certificadoRepository.save(existingCertificado);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Certificado> findById(Long id) {
        return certificadoRepository.findById(id);
    }

    @Override
    public List<Object> CertificadoQuery1(String nombre) {
        return certificadoRepository.CertificadoQuery1(nombre);
    }

    @Override
    public void delete(Long id) {
        certificadoRepository.deleteById(id);
    }
}
