package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.CertificadoDTO;
import pe.edu.upc.examenfinal.dtos.PeticionDTO;
import pe.edu.upc.examenfinal.entities.Certificado;
import pe.edu.upc.examenfinal.entities.Peticion;
import pe.edu.upc.examenfinal.servicesinterfaces.CertificadoService;
import pe.edu.upc.examenfinal.servicesinterfaces.PeticionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peticion")

public class PeticionController {
    @Autowired
    private PeticionService peticionService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<PeticionDTO> save(@RequestBody PeticionDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Peticion peticion = modelMapper.map(dto, Peticion.class);
        peticion = peticionService.save(peticion);
        PeticionDTO responseDto = modelMapper.map(peticion, PeticionDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<PeticionDTO>> list() {
        List<PeticionDTO> peticionDTOs = peticionService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, PeticionDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(peticionDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<PeticionDTO> update(@PathVariable Long id, @RequestBody PeticionDTO peticionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Peticion peticion = modelMapper.map(peticionDTO, Peticion.class);
        Peticion updated = peticionService.update(id, peticion);
        if (updated != null) {
            PeticionDTO responseDTO = modelMapper.map(updated, PeticionDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (peticionService.findById(id).isPresent()) {
            peticionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/intervalo-fecha")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<Object>> peticionIntervaloFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Object> result = peticionService.PeticionIntervaloFechaQuery(startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<Object>> peticionTipo(@PathVariable String tipo) {
        List<Object> result = peticionService.PeticionTipoQuery(tipo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<Object>> peticionEstado(@PathVariable String estado) {
        List<Object> result = peticionService.PeticionEstadoQuery(estado);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<Object> obtenerPeticionPorId(@PathVariable Long id) {
        Optional<Object> peticion = peticionService.obtenerPeticionPorId(id);
        return peticion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
