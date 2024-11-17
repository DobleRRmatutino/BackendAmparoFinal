package pe.edu.upc.examenfinal.servicesinterfaces;


import pe.edu.upc.examenfinal.entities.Certificado;

import java.util.List;
import java.util.Optional;

public interface CertificadoService {
    public Certificado save(Certificado certificado);
    public List<Certificado> list();
    public Certificado update(Long id, Certificado certificado);
    public Optional<Certificado> findById(Long id);
    public List<Object> CertificadoQuery1(String nombre);
    public void delete(Long id);
}
