package com.senac.certificacao.services;

import com.senac.certificacao.dto.PerfilDTO;
import com.senac.certificacao.entities.Perfil;
import com.senac.certificacao.exceptions.DatabaseException;
import com.senac.certificacao.exceptions.ResourceNotFoundException;
import com.senac.certificacao.repositories.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    @Transactional(readOnly = true)
    public PerfilDTO findById(Long id) {
        Perfil perfil = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PerfilDTO(perfil);
    }

    @Transactional(readOnly = true)
    public Page<PerfilDTO> findAll(Pageable pageable) {
        Page<Perfil> result = repository.findAll(pageable);
        return result.map(PerfilDTO::new);
    }

    @Transactional
    public PerfilDTO insert(PerfilDTO dto) {
        Perfil entity = new Perfil();
        entity.setNomePerfil(dto.getNomePerfil());
        entity = repository.save(entity);
        return new PerfilDTO(entity);
    }

    @Transactional
    public PerfilDTO update(Long id, PerfilDTO dto) {
        try {
            Perfil entity = repository.getReferenceById(id);
            entity.setNomePerfil(dto.getNomePerfil());
            repository.save(entity);
            return new PerfilDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


}
