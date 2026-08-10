package com.senac.certificacao.controllers;

import com.senac.certificacao.dto.AreaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.senac.certificacao.services.AreaService;
import java.net.URI;

@RestController
@RequestMapping(value = "/areas")
public class AreaController {

    @Autowired
    private AreaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> findById(@PathVariable Long id) {
        AreaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<AreaDTO>> findAll(Pageable pageable) {
        Page<AreaDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> insert(@Valid @RequestBody AreaDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> update(@PathVariable Long id, @Valid @RequestBody AreaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
