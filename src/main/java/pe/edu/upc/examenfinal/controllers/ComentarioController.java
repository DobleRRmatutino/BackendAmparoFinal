package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.ComentarioDTO;
import pe.edu.upc.examenfinal.entities.Comentario;
import pe.edu.upc.examenfinal.servicesinterfaces.ComentarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentario")

public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<ComentarioDTO> save(@RequestBody ComentarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Comentario comentario = modelMapper.map(dto, Comentario.class);
        // El id debe ser null o no asignado, para que la base de datos lo genere automáticamente.
        comentario.setId(null);
        comentario = comentarioService.save(comentario);
        ComentarioDTO responseDto = modelMapper.map(comentario, ComentarioDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping()
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<ComentarioDTO>> list() {
        List<ComentarioDTO> comentarioDTOs = comentarioService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, ComentarioDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(comentarioDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<ComentarioDTO> update(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        Comentario updated = comentarioService.update(id, comentario);
        if (updated != null) {
            ComentarioDTO responseDTO = modelMapper.map(updated, ComentarioDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (comentarioService.findById(id).isPresent()) {
            comentarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
