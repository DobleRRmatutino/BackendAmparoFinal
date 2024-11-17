package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.PeticionDTO;
import pe.edu.upc.examenfinal.dtos.PeticionUsuarioDTO;
import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.entities.PeticionUsuario;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionService;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peticionUsuario")

public class PeticionUsuarioController {
    @Autowired
    private PeticionUsuarioService peticionUsuarioService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<PeticionUsuarioDTO> save(@RequestBody PeticionUsuarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        PeticionUsuario peticionUsuario = modelMapper.map(dto, PeticionUsuario.class);
        peticionUsuario = peticionUsuarioService.save(peticionUsuario);
        PeticionUsuarioDTO responseDto = modelMapper.map(peticionUsuario, PeticionUsuarioDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<PeticionUsuarioDTO>> list() {
        List<PeticionUsuarioDTO> peticionUsuarioDTOS = peticionUsuarioService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, PeticionUsuarioDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(peticionUsuarioDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<PeticionUsuarioDTO> update(@PathVariable Long id, @RequestBody PeticionUsuarioDTO peticionUsuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        PeticionUsuario peticionUsuario = modelMapper.map(peticionUsuarioDTO, PeticionUsuario.class);
        PeticionUsuario updated = peticionUsuarioService.update(id, peticionUsuario);
        if (updated != null) {
            PeticionUsuarioDTO responseDTO = modelMapper.map(updated, PeticionUsuarioDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (peticionUsuarioService.findById(id).isPresent()) {
            peticionUsuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
