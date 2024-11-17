package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Sesion;
import pe.edu.upc.examenfinal.repositories.SesionRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.SesionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class SesionServiceImplements implements SesionService {


    @Autowired
    private SesionRepository sesionRepository;

    @Override
    public Sesion save(Sesion sesion) {
        return sesionRepository.save(sesion);
    }

    @Override
    public List<Sesion> list() {
        return sesionRepository.findAll();
    }

    @Override
    public Sesion update(Long id, Sesion updatedSesion) {
        Optional<Sesion> existingSesionOptional = sesionRepository.findById(id);
        if (existingSesionOptional.isPresent()) {
            Sesion existingSesion = existingSesionOptional.get();
            existingSesion.setFecha(updatedSesion.getFecha());
            existingSesion.setTitulo(updatedSesion.getTitulo());
            existingSesion.setDescripcion(updatedSesion.getDescripcion());
            existingSesion.setHora_inicio(updatedSesion.getHora_inicio());
            existingSesion.setHora_fin(updatedSesion.getHora_fin());
            existingSesion.setLink_sesion(updatedSesion.getLink_sesion());
            existingSesion.setLink_grabacion(updatedSesion.getLink_grabacion());
            existingSesion.setEstado(updatedSesion.getEstado());
            return sesionRepository.save(existingSesion);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Sesion> findById(Long id) {
        return sesionRepository.findById(id);
    }

    @Override
    public List<Object> SesionFechaIntervaloQuery(LocalDate startDate, LocalDate endDate) {
        return sesionRepository.SesionFechaIntervaloQuery(startDate,endDate);
    }

    @Override
    public void delete(Long id) {
        sesionRepository.deleteById(id);
    }


}
