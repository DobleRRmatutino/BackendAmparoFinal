package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.UsersDTO;
import pe.edu.upc.examenfinal.entities.Users;
import pe.edu.upc.examenfinal.repositories.UserRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.UsersService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping()
    public ResponseEntity<UsersDTO> save(@RequestBody UsersDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Users users = modelMapper.map(dto, Users.class);
        users = usersService.save(users);
        UsersDTO responseDto = modelMapper.map(users, UsersDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
        public ResponseEntity<List<UsersDTO>> list() {
        List<UsersDTO> usersDTOS = usersService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsersDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(usersDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<UsersDTO> update(@PathVariable Long id, @RequestBody UsersDTO usersDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Users users = modelMapper.map(usersDTO, Users.class);
        Users updated = usersService.update(id, users);
        if (updated != null) {
            UsersDTO responseDTO = modelMapper.map(updated, UsersDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (usersService.findById(id).isPresent()) {
            usersService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/psicologo")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public List<Users> getPsicologoUsers() {
        return usersService.getPsicologoUsers();
    }

    // Endpoint para obtener usuarios con rol ABOGADO
    @GetMapping("/abogado")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public List<Users> getAbogadoUsers() {
        return usersService.getAbogadoUsers();
    }

    // Endpoint para obtener usuarios con rol VICTIMA
    @GetMapping("/victima")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public List<Users> getVictimaUsers() {
        return usersService.getVictimaUsers();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsersDTO> getUserByUsername(@PathVariable String username) {
        Users user = usersService.findByUsername(username);
        if (user != null) {
            ModelMapper modelMapper = new ModelMapper();
            UsersDTO userDTO = modelMapper.map(user, UsersDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
