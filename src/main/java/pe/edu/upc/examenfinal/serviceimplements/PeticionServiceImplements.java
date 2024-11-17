package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.repositories.PeticionRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class PeticionServiceImplements implements PeticionService {

    @Autowired
    private PeticionRepository peticionRepository;

    @Override
    public Peticion save(Peticion peticion) {
        return peticionRepository.save(peticion);
    }

    @Override
    public List<Peticion> list() {
        return peticionRepository.findAll();
    }

    @Override
    public Peticion update(Long id, Peticion updatedPeticion) {
        Optional<Peticion> existingPeticionOptional = peticionRepository.findById(id);
        if (existingPeticionOptional.isPresent()) {
            Peticion existingPeticion = existingPeticionOptional.get();
            existingPeticion.setTitulo(updatedPeticion.getTitulo());
            existingPeticion.setDescripcion(updatedPeticion.getDescripcion());
            existingPeticion.setTipo(updatedPeticion.getTipo());
            existingPeticion.setFecha(updatedPeticion.getFecha());
            existingPeticion.setEstado(updatedPeticion.getEstado());
            return peticionRepository.save(existingPeticion);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Peticion> findById(Long id) {
        return peticionRepository.findById(id);
    }

    @Override
    public List<Object> PeticionIntervaloFechaQuery(LocalDate startDate, LocalDate endDate) {
        return peticionRepository.PeticionIntervaloFechaQuery(startDate,endDate);
    }

    @Override
    public List<Object> PeticionTipoQuery(String tipo) {
        return peticionRepository.PeticionTipoQuery(tipo);
    }

    @Override
    public List<Object> PeticionEstadoQuery(String estado) {
        return peticionRepository.PeticionEstadoQuery(estado);
    }

    @Override
    public void delete(Long id) {
        peticionRepository.deleteById(id);
    }

    @Override
    public Optional<Object> obtenerPeticionPorId(Long id) {
        return peticionRepository.obtenerPeticionPorId(id);
    }

}
