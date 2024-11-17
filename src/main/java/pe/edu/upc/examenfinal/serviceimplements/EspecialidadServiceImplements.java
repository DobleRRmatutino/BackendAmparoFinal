package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Especialidad;
import pe.edu.upc.examenfinal.repositories.EspecialidadRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.EspecialidadService;

import java.util.List;
import java.util.Optional;

@Service

public class EspecialidadServiceImplements implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public Especialidad save(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public List<Especialidad> list() {
        return especialidadRepository.findAll();
    }

    @Override
    public Especialidad update(Long id, Especialidad updatedEspecialidad) {
        Optional<Especialidad> existingEspecialidadOptional = especialidadRepository.findById(id);
        if (existingEspecialidadOptional.isPresent()) {
            Especialidad existingEspecialidad = existingEspecialidadOptional.get();
            existingEspecialidad.setEspecialidad_nombre(updatedEspecialidad.getEspecialidad_nombre());
            existingEspecialidad.setNumero_colegiatura(updatedEspecialidad.getNumero_colegiatura());
            existingEspecialidad.setDescripcion(updatedEspecialidad.getDescripcion());
            return especialidadRepository.save(existingEspecialidad);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Especialidad> findById(Long id) {
        return especialidadRepository.findById(id);
    }

    @Override
    public List<Object> EspecialidadQuery1(String nombre) {
        return especialidadRepository.EspecialidadQuery1(nombre);
    }

    @Override
    public void delete(Long id) {
        especialidadRepository.deleteById(id);
    }

    @Override
    public List<Especialidad> obtenerEspecialidadesPorUsuario(Long usuarioId) {
        return especialidadRepository.obtenerEspecialidadesPorUsuarioId(usuarioId); // Correcto llamado al método del repositorio
    }

}
