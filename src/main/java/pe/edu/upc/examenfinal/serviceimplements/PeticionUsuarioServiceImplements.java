package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;
import pe.edu.upc.examenfinal.repositories.PeticionUsuarioRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionUsuarioService;

import java.util.List;
import java.util.Optional;

@Service

public class PeticionUsuarioServiceImplements implements PeticionUsuarioService {

    @Autowired
    private PeticionUsuarioRepository peticionUsuarioRepository;

    @Override
    public PeticionUsuario save(PeticionUsuario peticionusuario) {
        return peticionUsuarioRepository.save(peticionusuario);
    }

    @Override
    public List<PeticionUsuario> list() {
        return peticionUsuarioRepository.findAll();
    }

    @Override
    public PeticionUsuario update(Long id, PeticionUsuario updatedPeticionUsuario) {
        Optional<PeticionUsuario> existingPeticionUsuarioOptional = peticionUsuarioRepository.findById(id);
        if (existingPeticionUsuarioOptional.isPresent()) {
            PeticionUsuario existingPeticionUsuario = existingPeticionUsuarioOptional.get();
            existingPeticionUsuario.setFecha(updatedPeticionUsuario.getFecha());
            return peticionUsuarioRepository.save(existingPeticionUsuario);
        } else {
            return null;
        }
    }

    @Override
    public Optional<PeticionUsuario> findById(Long id) {
        return peticionUsuarioRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        peticionUsuarioRepository.deleteById(id);
    }


}
