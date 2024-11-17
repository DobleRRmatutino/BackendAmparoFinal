package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.SesionDTO;
import pe.edu.upc.examenfinal.entities.Sesion;
import pe.edu.upc.examenfinal.servicesinterfaces.SesionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sesion")

public class SesionController {
    @Autowired
    private SesionService sesionService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<SesionDTO> save(@RequestBody SesionDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Sesion sesion = modelMapper.map(dto, Sesion.class);
        sesion = sesionService.save(sesion);
        SesionDTO responseDto = modelMapper.map(sesion, SesionDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<SesionDTO>> list() {
        List<SesionDTO> sesionDTOS = sesionService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, SesionDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(sesionDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<SesionDTO> update(@PathVariable Long id, @RequestBody SesionDTO sesionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Sesion sesion = modelMapper.map(sesionDTO, Sesion.class);
        Sesion updated = sesionService.update(id, sesion);
        if (updated != null) {
            SesionDTO responseDTO = modelMapper.map(updated, SesionDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (sesionService.findById(id).isPresent()) {
            sesionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionDTO> findById(@PathVariable Long id) {
        Optional<Sesion> sesionOptional = sesionService.findById(id);
        if (sesionOptional.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            SesionDTO sesionDTO = modelMapper.map(sesionOptional.get(), SesionDTO.class);
            return new ResponseEntity<>(sesionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fecha-intervalo")
    public ResponseEntity<List<Object>> sesionFechaIntervalo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Object> result = sesionService.SesionFechaIntervaloQuery(startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
