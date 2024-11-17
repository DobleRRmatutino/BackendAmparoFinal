package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.PeticionDTO;
import pe.edu.upc.examenfinal.dtos.RefugioDTO;
import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.entities.Refugio;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionService;
import pe.edu.upc.examenfinal.servicesinterfaces.RefugioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refugio")

public class RefugioController {
    @Autowired
    private RefugioService refugioService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<RefugioDTO> save(@RequestBody RefugioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Refugio refugio  = modelMapper.map(dto, Refugio.class);
        refugio = refugioService.save(refugio);
        RefugioDTO responseDto = modelMapper.map(refugio, RefugioDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<RefugioDTO>> list() {
        List<RefugioDTO> refugioDTOS = refugioService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, RefugioDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(refugioDTOS, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<RefugioDTO> update(@PathVariable Long id, @RequestBody RefugioDTO refugioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Refugio refugio = modelMapper.map(refugioDTO, Refugio.class);
        Refugio updated = refugioService.update(id, refugio);
        if (updated != null) {
            RefugioDTO responseDTO = modelMapper.map(updated, RefugioDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (refugioService.findById(id).isPresent()) {
            refugioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Object>> refugioDepartamento(@PathVariable String departamento) {
        List<Object> result = refugioService.RefugioDepartamentoQuery(departamento);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/distrito/{distrito}")
    public ResponseEntity<List<Object>> refugioDistrito(@PathVariable String distrito) {
        List<Object> result = refugioService.RefugioDistritoQuery(distrito);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
