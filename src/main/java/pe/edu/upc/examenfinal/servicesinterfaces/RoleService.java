package pe.edu.upc.examenfinal.servicesinterfaces;


import pe.edu.upc.examenfinal.entities.Role;

import java.util.List;

public interface RoleService {
    public Role save(Role role);
    public List<Role> list();
    public Role update(Long id, Role role);
}
