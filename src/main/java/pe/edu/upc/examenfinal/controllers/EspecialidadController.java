package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.DocumentoDTO;
import pe.edu.upc.examenfinal.dtos.EspecialidadDTO;
import pe.edu.upc.examenfinal.entities.Documento;
import pe.edu.upc.examenfinal.entities.Especialidad;
import pe.edu.upc.examenfinal.servicesinterfaces.DocumentoService;
import pe.edu.upc.examenfinal.servicesinterfaces.EspecialidadService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidad")

public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
    public ResponseEntity<EspecialidadDTO> save(@RequestBody EspecialidadDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Especialidad especialidad = modelMapper.map(dto, Especialidad.class);
        especialidad = especialidadService.save(especialidad);
        EspecialidadDTO responseDto = modelMapper.map(especialidad, EspecialidadDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<EspecialidadDTO>> list() {
        List<EspecialidadDTO> especialidadDTOs = especialidadService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, EspecialidadDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(especialidadDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
    public ResponseEntity<EspecialidadDTO> update(@PathVariable Long id, @RequestBody EspecialidadDTO especialidadDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Especialidad especialidad = modelMapper.map(especialidadDTO, Especialidad.class);
        Especialidad updated = especialidadService.update(id, especialidad);
        if (updated != null) {
            EspecialidadDTO responseDTO = modelMapper.map(updated, EspecialidadDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
            @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
            public ResponseEntity<Void> delete(@PathVariable Long id) {
                if (especialidadService.findById(id).isPresent()) {
                    especialidadService.delete(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/query1/{nombre}")
    public ResponseEntity<List<Object>> especialidadQuery1(@PathVariable String nombre) {
        List<Object> result = especialidadService.EspecialidadQuery1(nombre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Especialidad>> obtenerEspecialidadesPorUsuario(@PathVariable Long usuarioId) {
        List<Especialidad> especialidades = especialidadService.obtenerEspecialidadesPorUsuario(usuarioId);
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }
}
