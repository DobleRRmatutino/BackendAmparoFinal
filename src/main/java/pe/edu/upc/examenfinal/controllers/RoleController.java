package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.PeticionUsuarioDTO;
import pe.edu.upc.examenfinal.dtos.RolDTO;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;
import pe.edu.upc.examenfinal.entities.Role;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionUsuarioService;
import pe.edu.upc.examenfinal.servicesinterfaces.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")

public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<RolDTO> save(@RequestBody RolDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(dto, Role.class);
        role = roleService.save(role);
        RolDTO responseDto = modelMapper.map(role, RolDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<RolDTO>> list() {
        List<RolDTO> roleDTOS = roleService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, RolDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<RolDTO> update(@PathVariable Long id, @RequestBody RolDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDTO, Role.class);
        Role updated = roleService.update(id, role);
        if (updated != null) {
            RolDTO responseDTO = modelMapper.map(updated, RolDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
