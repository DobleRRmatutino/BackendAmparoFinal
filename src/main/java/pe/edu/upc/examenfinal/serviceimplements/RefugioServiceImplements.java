package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Refugio;
import pe.edu.upc.examenfinal.repositories.RefugioRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.RefugioService;

import java.util.List;
import java.util.Optional;

@Service

public class RefugioServiceImplements implements RefugioService {

    @Autowired
    private RefugioRepository refugioRepository;

    @Override
    public Refugio save(Refugio refugio) {
        return refugioRepository.save(refugio);
    }

    @Override
    public List<Refugio> list() {
        return refugioRepository.findAll();
    }

    @Override
    public Refugio update(Long id, Refugio updatedRefugio) {
        Optional<Refugio> existingRefugioOptional = refugioRepository.findById(id);
        if (existingRefugioOptional.isPresent()) {
            Refugio existingRefugio = existingRefugioOptional.get();
            existingRefugio.setNombre(updatedRefugio.getNombre());
            existingRefugio.setDepartamento(updatedRefugio.getDepartamento());
            existingRefugio.setDistrito(updatedRefugio.getDistrito());
            existingRefugio.setDireccion(updatedRefugio.getDireccion());
            existingRefugio.setTelefono(updatedRefugio.getTelefono());
            return refugioRepository.save(existingRefugio);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Refugio> findById(Long id) {
        return refugioRepository.findById(id);
    }


    @Override
    public void delete(Long id) {
        refugioRepository.deleteById(id);
    }
    @Override
    public List<Object> RefugioDepartamentoQuery(String departamento) {
        return refugioRepository.RefugioDepartamentoQuey(departamento);
    }

    @Override
    public List<Object> RefugioDistritoQuery(String distrito) {
        return refugioRepository.RefugioDistritoQuery(distrito);
    }


}
