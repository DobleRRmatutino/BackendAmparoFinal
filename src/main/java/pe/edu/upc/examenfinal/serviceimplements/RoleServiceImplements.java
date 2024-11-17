package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Role;
import pe.edu.upc.examenfinal.repositories.RoleRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplements implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @Override
    public Role update(Long id, Role updatedRole) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();
            existingRole.setRol(updatedRole.getRol());
            return roleRepository.save(existingRole);
        } else {
            return null;
        }
    }

}
