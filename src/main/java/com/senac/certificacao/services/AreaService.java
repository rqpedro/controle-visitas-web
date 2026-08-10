package com.senac.certificacao.services;


import com.senac.certificacao.dto.AreaDTO;
import com.senac.certificacao.entities.Area;
import com.senac.certificacao.exceptions.DatabaseException;
import com.senac.certificacao.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.senac.certificacao.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository repository;

    @Transactional(readOnly = true)
    public AreaDTO findById(Long id) {
        Area area = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AreaDTO(area);
    }

    @Transactional(readOnly = true)
    public Page<AreaDTO> findAll(Pageable pageable) {
        Page<Area> result = repository.findAll(pageable);
        return result.map(AreaDTO::new);
    }

    @Transactional
    public AreaDTO insert(AreaDTO dto) {
        Area entity = new Area();
        entity.setNomeArea(dto.getNomeArea());
        entity = repository.save(entity);
        return new AreaDTO(entity);
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        try {
            Area entity = repository.getReferenceById(id);
            entity.setNomeArea(dto.getNomeArea());
            entity = repository.save(entity);
            return new AreaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resultado não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
