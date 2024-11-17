package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Refugio;
import pe.edu.upc.examenfinal.entities.Sesion;
import pe.edu.upc.examenfinal.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public Users save(Users users);
    public List<Users> list();
    public Users update(Long id, Users users);
    public Optional<Users> findById(Long id);
    public void delete(Long id);
    List<Users> getPsicologoUsers();
    List<Users> getAbogadoUsers();
    List<Users> getVictimaUsers();
    Users findByUsername(String username);
}
