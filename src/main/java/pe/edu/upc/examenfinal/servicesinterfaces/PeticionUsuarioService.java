package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;

import java.util.List;
import java.util.Optional;

public interface PeticionUsuarioService {
    public PeticionUsuario save(PeticionUsuario peticionusuario);
    public List<PeticionUsuario> list();
    public PeticionUsuario update(Long id, PeticionUsuario peticionUsuario);
    public Optional<PeticionUsuario> findById(Long id);
    public void delete(Long id);
}
