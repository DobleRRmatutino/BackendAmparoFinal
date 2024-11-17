package pe.edu.upc.examenfinal.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;
import pe.edu.upc.examenfinal.entities.Refugio;

import java.util.List;
import java.util.Optional;

public interface RefugioService {
    public Refugio save(Refugio refugio);
    public List<Refugio> list();
    public Refugio update(Long id, Refugio refugio);
    public Optional<Refugio> findById(Long id);
    public void delete(Long id);
    public List<Object> RefugioDepartamentoQuery(String departamento);
    public List<Object> RefugioDistritoQuery(String distrito);
}
