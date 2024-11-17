package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.DocumentoDTO;
import pe.edu.upc.examenfinal.entities.Documento;
import pe.edu.upc.examenfinal.servicesinterfaces.DocumentoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documento")

public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
    public ResponseEntity<DocumentoDTO> save(@RequestBody DocumentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Documento documento = modelMapper.map(dto, Documento.class);
        documento = documentoService.save(documento);
        DocumentoDTO responseDto = modelMapper.map(documento, DocumentoDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO','VICTIMA', 'ADMINISTRADOR')")
    public ResponseEntity<List<DocumentoDTO>> list() {
        List<DocumentoDTO> documentoDTOs = documentoService.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, DocumentoDTO.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(documentoDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PSICOLOGO','ABOGADO', 'ADMINISTRADOR')")
    public ResponseEntity<DocumentoDTO> update(@PathVariable Long id, @RequestBody DocumentoDTO documentoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Documento documento = modelMapper.map(documentoDTO, Documento.class);
        Documento updated = documentoService.update(id, documento);
        if (updated != null) {
            DocumentoDTO responseDTO = modelMapper.map(updated, DocumentoDTO.class);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (documentoService.findById(id).isPresent()) {
            documentoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
