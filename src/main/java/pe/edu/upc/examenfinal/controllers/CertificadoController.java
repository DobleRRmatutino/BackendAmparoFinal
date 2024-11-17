package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.CertificadoDTO;
import pe.edu.upc.examenfinal.entities.Certificado;
import pe.edu.upc.examenfinal.servicesinterfaces.CertificadoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/certificado")

//ADMINISTRADOR
//VICTIMA
//TRABAJADOR

public class CertificadoController {
    @Autowired
    private CertificadoService certificadoService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
        public ResponseEntity<CertificadoDTO> save(@RequestBody CertificadoDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
        Certificado certificado = modelMapper.map(dto, Certificado.class);
        certificado = certificadoService.save(certificado);
        CertificadoDTO responseDto = modelMapper.map(certificado, CertificadoDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<CertificadoDTO>> list() {
        List<CertificadoDTO> certificadoDTOs = certificadoService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, CertificadoDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(certificadoDTOs, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
    public ResponseEntity<CertificadoDTO> update(@PathVariable Long id, @RequestBody CertificadoDTO certificadoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Certificado certificado = modelMapper.map(certificadoDTO, Certificado.class);
        Certificado updated = certificadoService.update(id, certificado);
        if (updated != null) {
            CertificadoDTO responseDTO = modelMapper.map(updated, CertificadoDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (certificadoService.findById(id).isPresent()) {
            certificadoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/query1/{nombre}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<Object>> certificadoQuery1(@PathVariable String nombre) {
        List<Object> result = certificadoService.CertificadoQuery1(nombre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
