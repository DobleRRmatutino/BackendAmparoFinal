package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Users;
import pe.edu.upc.examenfinal.repositories.UserRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.UsersService;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImplements implements UsersService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImplements(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Users save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Override
    public List<Users> list() {
        return userRepository.findAll();
    }

    @Override
    public Users update(Long id, Users updatedUser) {
        Optional<Users> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            } else {
                existingUser.setPassword(existingUser.getPassword());
            }

            existingUser.setEnabled(updatedUser.getEnabled());
            existingUser.setName(updatedUser.getName());
            existingUser.setSurnames(updatedUser.getSurnames());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> getPsicologoUsers() {
        return userRepository.findUsersByRolePsicologo();
    }

    @Override
    public List<Users> getAbogadoUsers() {
        return userRepository.findUsersByRoleAbogado();
    }

    @Override
    public List<Users> getVictimaUsers() {
        return userRepository.findUsersByRoleVictima();
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
